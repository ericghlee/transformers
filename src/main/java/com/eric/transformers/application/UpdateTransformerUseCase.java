package com.eric.transformers.application;

import com.eric.transformers.domain.Transformer;
import com.eric.transformers.infrastructure.http.presentation.request.TransformerRequest;
import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTransformerUseCase {

    private final TransformerRepository transformerRepository;

    public UpdateTransformerUseCase(TransformerRepository transformerRepository) {
        this.transformerRepository = transformerRepository;
    }

    public Transformer execute(TransformerRequest request) {
        Transformer transformer = request.toTransformer();

        if (transformerRepository.findById(transformer.getId()).isEmpty()) {
            throw new IllegalArgumentException();
        }

        return transformerRepository.save(transformer);
    }
}
