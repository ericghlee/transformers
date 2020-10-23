package com.eric.transformers.application;

import com.eric.transformers.domain.Transformer;
import com.eric.transformers.infrastructure.http.presentation.CreateTransformerRequest;
import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateTransformerUseCaseTest {

    @Captor
    private ArgumentCaptor<Transformer> captor;

    @Mock
    private TransformerRepository repository;

    @InjectMocks
    private CreateTransformerUseCase createTransformerUseCase;

    @Test
    public void testExecute() {
        CreateTransformerRequest request = CreateTransformerRequest.builder()
                .id("Soundwave")
                .team("D")
                .strength(8)
                .intelligence(9)
                .speed(2)
                .endurance(6)
                .rank(7)
                .courage(5)
                .firepower(6)
                .build();

        createTransformerUseCase.execute(request);

        verify(repository).save(captor.capture());

        Transformer value = captor.getValue();

        assertEquals(request.getId(), value.getId());
        assertEquals(request.getTeam(), value.getTeamCode());
        assertEquals(request.getStrength(), value.getStrength());
        assertEquals(request.getIntelligence(), value.getIntelligence());
        assertEquals(request.getSpeed(), value.getSpeed());
        assertEquals(request.getEndurance(), value.getEndurance());
        assertEquals(request.getRank(), value.getRank());
        assertEquals(request.getCourage(), value.getCourage());
        assertEquals(request.getFirepower(), value.getFirepower());
    }
}