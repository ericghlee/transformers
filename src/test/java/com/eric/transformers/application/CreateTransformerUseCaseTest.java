package com.eric.transformers.application;

import com.eric.transformers.domain.Transformer;
import com.eric.transformers.fixture.TransformerFixture;
import com.eric.transformers.infrastructure.http.presentation.request.TransformerRequest;
import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        TransformerRequest request = TransformerFixture.getSoundwaveRequest();

        when(repository.findById(request.getId())).thenReturn(Optional.empty());

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

    @Test
    public void testExecute_already_exists() {
        TransformerRequest request = TransformerFixture.getSoundwaveRequest();

        when(repository.findById(request.getId())).thenReturn(Optional.of(mock(Transformer.class)));

        assertThrows(IllegalArgumentException.class, () -> {
            createTransformerUseCase.execute(request);
            verify(repository, never()).save(any());
        });
    }
}