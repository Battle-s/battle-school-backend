package org.battles.battles.school;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.battles.battles.common.TimeStamped;

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
