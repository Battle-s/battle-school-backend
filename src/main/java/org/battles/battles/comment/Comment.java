package org.battles.battles.comment;

import org.battles.battles.common.TimeStamped;

import javax.persistence.*;


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

    @Column(nullable = false)
    private String content;

}
