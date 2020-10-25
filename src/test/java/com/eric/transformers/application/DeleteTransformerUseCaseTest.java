package com.eric.transformers.application;

import com.eric.transformers.domain.Transformer;
import com.eric.transformers.fixture.TransformerFixture;
import com.eric.transformers.infrastructure.http.presentation.request.TransformerRequest;
import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class DeleteTransformerUseCaseTest {

    @Mock
    private TransformerRepository repository;

    @InjectMocks
    private DeleteTransformerUseCase deleteTransformerUseCase;


    @Test
    public void testExecute() {
        String id = "transformer";

        deleteTransformerUseCase.execute(id);

        verify(repository).findById(id);
    }

    @Test
    public void testExecute_does_not_exists() {
        String id = "transformer";

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            deleteTransformerUseCase.execute(id);
            verify(repository, never()).deleteById(any());
        });
    }
}