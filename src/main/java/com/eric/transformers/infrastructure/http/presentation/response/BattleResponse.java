package com.eric.transformers.infrastructure.http.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BattleResponse {

    @JsonProperty("total_battles")
    private Integer totalBattles;

    @JsonProperty("winner")
    private String winner;

    @JsonProperty("autobots_wins")
    private int autobotsWins;

    @JsonProperty("decepticons_wins")
    private int decepticonsWins;

    @JsonProperty("ties")
    private int ties;

    @JsonProperty("autobots_survivors")
    private Set<String> autobotsSurvivor;

    @JsonProperty("decepticons_survivors")
    private Set<String> decepticonsSurvivor;
}
