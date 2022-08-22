package org.battles.battles.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.common.TimeStamped;
import org.battles.battles.school.School;
import org.battles.battles.user.User;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Post_Table")
public class Post extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

     // fix me = fk mapping 필요
    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User user;

    // fix me = fk mapping 필요
    @ManyToOne
    @JoinColumn(name = "schoolId", nullable = true)
    private School school;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PostType postType;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Builder
    public Post(User user, School school, String title, String content, PostType postType, Status status) {
        this.user = user;
        this.school = school;
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.status = status;
    }

}