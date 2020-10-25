package com.eric.transformers.application;

import com.eric.transformers.domain.Transformer;
import com.eric.transformers.infrastructure.http.presentation.request.TransformerRequest;
import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTransformerUseCase {

    private final TransformerRepository transformerRepository;

    public DeleteTransformerUseCase(TransformerRepository transformerRepository) {
        this.transformerRepository = transformerRepository;
    }

    public void execute(String id) {

        if (transformerRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException();
        }

        transformerRepository.deleteById(id);
    }
}
