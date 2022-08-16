package org.battles.battles.battle.category;

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

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Area_Table")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long areaId;

    @Column(nullable = false)
    private String areaName;
}
