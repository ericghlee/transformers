package com.eric.transformers.domain;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Team {
    AUTOBOTS("A"),
    DECEPTICONS("D");

    private String teamCode;

    Team(String teamCode) {
        this.teamCode = teamCode;
    }

    public static Team of(String teamCode) {
        return Stream.of(Team.values())
                .filter(t -> t.getTeamCode().equals(teamCode))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
