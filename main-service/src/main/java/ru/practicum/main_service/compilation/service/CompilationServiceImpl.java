package ru.practicum.main_service.compilation.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_service.compilation.dto.CompilationDto;
import ru.practicum.main_service.compilation.dto.NewCompilationDto;
import ru.practicum.main_service.compilation.dto.UpdateCompilationRequest;
import ru.practicum.main_service.compilation.mapper.CompilationMapper;
import ru.practicum.main_service.compilation.model.Compilation;
import ru.practicum.main_service.compilation.repository.CompilationRepository;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.mapper.EventTool;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.repository.EventRepository;
import ru.practicum.main_service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CompilationServiceImpl implements CompilationService {
    private final EventRepository eventRepository;
    private final CompilationRepository compilationRepository;
    private final CompilationMapper compilationMapper;
    private final EventTool eventTool;

    @Override
    @Transactional
    public CompilationDto create(NewCompilationDto newCompilationDto) {
        log.info("Создание новой подборки событий с параметрами {}", newCompilationDto);

        Set<Event> events = new HashSet<>();

        if (!newCompilationDto.getEvents().isEmpty()) {
            events.addAll(eventRepository.findAllByIdIn(newCompilationDto.getEvents()));
            checkSize(events, newCompilationDto.getEvents());
        }

        Compilation compilation = compilationRepository.save(compilationMapper.newDtoToCompilation(newCompilationDto, events));

        return getById(compilation.getId());
    }

    @Override
    @Transactional
    public CompilationDto patch(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        log.info("Обновление подборки событий с id {} и новыми параметрами {}", compId, updateCompilationRequest);

        Compilation compilation = getCompilationById(compId);

        if (updateCompilationRequest.getTitle() != null) {
            compilation.setTitle(updateCompilationRequest.getTitle());
        }

        if (updateCompilationRequest.getPinned() != null) {
            compilation.setPinned(updateCompilationRequest.getPinned());
        }

        if (updateCompilationRequest.getEvents() != null) {
            Set<Event> events = new HashSet<Event>(eventRepository.findAllByIdIn(updateCompilationRequest.getEvents()));

            checkSize(events, updateCompilationRequest.getEvents());

            compilation.setEvents(events);
        }

        compilationRepository.save(compilation);

        return getById(compId);
    }

    @Override
    @Transactional
    public void deleteById(Long compId) {
        log.info("Удаление подборки событий с id {}", compId);

        getCompilationById(compId);

        compilationRepository.deleteById(compId);
    }

    @Override
    public List<CompilationDto> getAll(Boolean pinned, Pageable pageable) {
        log.info("Вывод всех подборок событий с параметрами pinned = {}, pageable = {}", pinned, pageable);

        List<Compilation> compilations;

        if (pinned == null) {
            compilations = compilationRepository.findAll(pageable).toList();
        } else {
            compilations = compilationRepository.findAllByPinned(pinned, pageable);
        }

        Set<Event> uniqueEvents = new HashSet<>();
        compilations.forEach(compilation -> uniqueEvents.addAll(compilation.getEvents()));

        Map<Long, EventShortDto> eventsShortDto = new HashMap<>();
        eventTool.toEventsShortDto(new ArrayList<>(uniqueEvents))
                .forEach(event -> eventsShortDto.put(event.getId(), event));

        List<CompilationDto> result = new ArrayList<>();
        compilations.forEach(compilation -> {
            Set<EventShortDto> compEventsShortDto = new HashSet<>();
            compilation.getEvents()
                    .forEach(event -> compEventsShortDto.add(eventsShortDto.get(event.getId())));
            result.add(compilationMapper.toCompilationDto(compilation, compEventsShortDto));
        });

        return result;
    }

    @Override
    public CompilationDto getById(Long compId) {
        log.info("Вывод подборки событий с id {}", compId);

        Compilation compilation = getCompilationById(compId);

        Set<EventShortDto> eventsShortDto = eventTool.toEventsShortDto(compilation.getEvents());

        return compilationMapper.toCompilationDto(compilation, eventsShortDto);
    }

    private Compilation getCompilationById(Long compId) {
        return compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Подборки с таким id не существует."));
    }

    private void checkSize(Collection<Event> events, Collection<Long> eventsIdToUpdate) {
        if (events.size() != eventsIdToUpdate.size()) {
            throw new NotFoundException("Некоторые события не найдены.");
        }
    }

}
