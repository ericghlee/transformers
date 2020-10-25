package com.eric.transformers.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class BattleStats {

    private final BattleResult result;

    private final int totalBattles;

    private final int autobotsWins;

    private final int decepticonsWins;

    private final int ties;

    private final List<Transformer> autobotsSurvivors;

    private final List<Transformer> decepticonSurvivors;
}
