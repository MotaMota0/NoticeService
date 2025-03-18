package com.notice.UserService.model;


/***
 create table comments(
 id bigserial primary key,
 task_id bigint not null,
 author_id bigint not null ,
 content  text not null,
 created_at  timestamp  default current_timestamp,
 foreign key (task_id) references tasks(id) on delete cascade,
 foreign key (author_id) references authors(id) on delete cascade
 );
***/

 import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String text;
    @ManyToOne

    @JoinColumn(name = "task_id", nullable = false) // Добавляем JoinColumn
    private Task task;
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }


}
