package org.battles.battles.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.battles.battles.common.Status;

import org.battles.battles.common.TimeStamped;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comment_Table")
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // fix me = fk mapping 필요
    @Column(nullable = false)
    private Long userId;

    // fix me = fk mapping 필요
    @Column(nullable = false)
    private Long postId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Builder
    public Comment(Long userId, Long postId, String content, Status status) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.status = status;
    }
}
