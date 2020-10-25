package com.eric.transformers.application;

import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ListTransformerUseCaseTest {

    @Mock
    private TransformerRepository transformerRepository;

    @InjectMocks
    private ListTransformerUseCase listTransformerUseCase;

    @Test
    public void testExecute() {
        listTransformerUseCase.execute();

        verify(transformerRepository).findAll();
    }
}