package org.battles.battles.school;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.battles.battles.common.TimeStamped;
import org.battles.battles.post.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "School_Table")
public class School extends TimeStamped {

    // fix me = id auto increment 생각필요
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long schoolId;

    @Column(nullable = false)
    private String schoolName;

    @Column(nullable = true)
    private String schoolDomainName;

}
