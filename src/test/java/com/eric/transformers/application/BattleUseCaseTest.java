package com.eric.transformers.application;

import com.eric.transformers.domain.BattleResult;
import com.eric.transformers.domain.BattleStats;
import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.eric.transformers.fixture.TransformerFixture.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BattleUseCaseTest {

    @Mock
    private TransformerRepository transformerRepository;

    @InjectMocks
    private BattleUseCase battleUseCase;

    @Test
    public void testExecute() {
        when(transformerRepository.findAll()).thenReturn(asList(
                getBluestreakRequest().toTransformer(),
                getSoundwaveRequest().toTransformer(),
                getHubcapRequest().toTransformer()
                ));

        BattleStats stats = battleUseCase.execute();

        assertEquals(BattleResult.DECEPTICONS, stats.getResult());
        assertEquals(0, stats.getAutobotsWins());
        assertEquals(1, stats.getDecepticonsWins());
        assertEquals(0, stats.getTies());
        assertTrue(stats.getDecepticonSurvivors().stream().anyMatch(it -> "Soundwave".equals(it.getId())));
        assertTrue(stats.getAutobotsSurvivors().stream().anyMatch(it -> "Hubcap".equals(it.getId())));
    }
}