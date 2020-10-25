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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class UpdateTransformerUseCaseTest {

    @Captor
    private ArgumentCaptor<Transformer> captor;

    @Mock
    private TransformerRepository repository;

    @InjectMocks
    private UpdateTransformerUseCase updateTransformerUseCase;

    @Test
    public void testExecute() {
        TransformerRequest request = TransformerFixture.getSoundwaveRequest();

        updateTransformerUseCase.execute(request);

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
    public void testExecute_does_not_exists() {
        TransformerRequest request = TransformerFixture.getSoundwaveRequest();

        when(repository.findById(request.getId())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            updateTransformerUseCase.execute(request);
            verify(repository, never()).save(any());
        });
    }
}