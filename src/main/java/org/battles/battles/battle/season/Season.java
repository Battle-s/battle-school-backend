package org.battles.battles.battle.season;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.battles.battles.common.Status;
import org.battles.battles.common.TimeStamped;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Season_Table")
public class Season extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seasonId;

    @Column(nullable = false)
    private String seasonName;

    @Column(nullable = false)
    private LocalDateTime seasonStart;

    @Column(nullable = false)
    private LocalDateTime seasonEnd;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
