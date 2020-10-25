package com.eric.transformers.application;

import com.eric.transformers.domain.Transformer;
import com.eric.transformers.infrastructure.http.presentation.request.TransformerRequest;
import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTransformerUseCase {

    private final TransformerRepository transformerRepository;

    public ListTransformerUseCase(TransformerRepository transformerRepository) {
        this.transformerRepository = transformerRepository;
    }

    public List<Transformer> execute() {
        return transformerRepository.findAll();
    }
}
