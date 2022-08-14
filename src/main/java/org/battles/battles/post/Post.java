package org.battles.battles.post;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.battles.battles.common.Status;
=======
>>>>>>> ed7e99aa30fa1d10ab6f4a36e382de1467342968
import org.battles.battles.common.TimeStamped;

import javax.persistence.*;

<<<<<<< HEAD
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Post_Table")
public class Post extends TimeStamped{
=======
@Entity
@Table(name = "Post_Table")

public class Post extends TimeStamped {
>>>>>>> ed7e99aa30fa1d10ab6f4a36e382de1467342968
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

<<<<<<< HEAD
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Builder
    public Post(Long userId, Long schoolId, String title, String content, PostType postType, Status status) {
        this.userId = userId;
        this.schoolId = schoolId;
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.status = status;
    }

=======
>>>>>>> ed7e99aa30fa1d10ab6f4a36e382de1467342968
}