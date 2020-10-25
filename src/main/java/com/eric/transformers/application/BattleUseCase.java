package com.eric.transformers.application;

import com.eric.transformers.application.util.BattleUtil;
import com.eric.transformers.domain.BattleResult;
import com.eric.transformers.domain.BattleStats;
import com.eric.transformers.domain.Team;
import com.eric.transformers.domain.Transformer;
import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.eric.transformers.domain.BattleResult.*;
import static java.util.stream.Collectors.groupingBy;

@Service
public class BattleUseCase {

    private final TransformerRepository transformerRepository;


    public BattleUseCase(TransformerRepository transformerRepository) {
        this.transformerRepository = transformerRepository;
    }

    public BattleStats execute() {
        Map<Team, List<Transformer>> transformers = transformerRepository.findAll().stream()
                .collect(groupingBy(Transformer::getTeam));

        List<Transformer> decepticons = transformers.get(Team.DECEPTICONS).stream()
                .sorted((o1, o2) -> o2.getRank().compareTo(o1.getRank()))
                .collect(Collectors.toList());

        List<Transformer> autobots = transformers.get(Team.AUTOBOTS).stream()
                .sorted((o1, o2) -> o2.getRank().compareTo(o1.getRank()))
                .collect(Collectors.toList());

        int loopSize = Math.min(autobots.size(), decepticons.size());

        List<Transformer> autobotsSurvivors = new ArrayList<>(autobots);
        List<Transformer> decepticonsSurvivors = new ArrayList<>(decepticons);

        int autobotsWins = 0;
        int decepticonsWins = 0;
        int ties = 0;

        for (int i = 0; i < loopSize; i++) {
            BattleResult result = BattleUtil.battle(autobots.get(i), decepticons.get(i));

            switch (result) {
                case END:
                    return BattleStats.builder()
                            .totalBattles(autobotsWins + decepticonsWins + ties)
                            .result(result)
                            .build();

                case AUTOBOTS:
                    decepticonsSurvivors.remove(decepticons.get(i));
                    autobotsWins++;
                    break;

                case DECEPTICONS:
                    autobotsSurvivors.remove(autobots.get(i));
                    decepticonsWins++;
                    break;

                case TIE:
                    ties++;
                    decepticonsSurvivors.remove(decepticons.get(i));
                    autobotsSurvivors.remove(autobots.get(i));
                    break;
            }
        }

        return BattleStats.builder()
                .totalBattles(autobotsWins + decepticonsWins + ties)
                .autobotsWins(autobotsWins)
                .decepticonsWins(decepticonsWins)
                .ties(ties)
                .autobotsSurvivors(autobotsSurvivors)
                .decepticonSurvivors(decepticonsSurvivors)
                .result(autobotsWins == decepticonsWins ? TIE : (autobotsWins > decepticonsWins ? AUTOBOTS : DECEPTICONS))
                .build();
    }

}
