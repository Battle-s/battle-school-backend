package org.battles.battles.post;

import org.battles.battles.common.TimeStamped;

import javax.persistence.*;

@Entity
@Table(name = "Post_Table")
public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

}
