package com.eric.transformers.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity(name = "transformer")
public class Transformer {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "team")
    @Basic
    private String teamCode;

    @Transient
    private Team team;

    @Column(name = "strength")
    private Integer strength;

    @Column(name = "intelligence")
    private Integer intelligence;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "endurance")
    private Integer endurance;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "courage")
    private Integer courage;

    @Column(name = "firepower")
    private Integer firepower;

    @PostLoad
    void fillTransient() {
        if (teamCode != null) {
            this.team = Team.of(teamCode);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (team != null) {
            this.teamCode = team.getTeamCode();
        }
    }

    public Integer getRating() {
        return strength + intelligence + speed + endurance + firepower;
    }
}
