package org.battles.battles.post;

import org.battles.battles.common.TimeStamped;

import javax.persistence.*;

@Entity
@Table(name = "Post_Table")

public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    // fix me = fk mapping 필요
    @Column(nullable = false)
    private Long userId;

    // fix me = fk mapping 필요
    @Column(nullable = true)
    private Long schoolId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PostType postType;

}