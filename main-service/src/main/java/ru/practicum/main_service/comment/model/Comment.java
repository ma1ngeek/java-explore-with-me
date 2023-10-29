package ru.practicum.main_service.comment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.practicum.main_service.MainCommon;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.user.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = MainCommon.MAX_LENGTH_COMMENT, nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Event event;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    private LocalDateTime editedOn;
}
