package com.eric.transformers.application;

import com.eric.transformers.domain.Transformer;
import com.eric.transformers.infrastructure.http.presentation.CreateTransformerRequest;
import com.eric.transformers.infrastructure.repository.TransformerRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTransformerUseCase {

    private TransformerRepository transformerRepository;

    public CreateTransformerUseCase(TransformerRepository transformerRepository) {
        this.transformerRepository = transformerRepository;
    }

    public Transformer execute(CreateTransformerRequest request) {
        Transformer transformer = Transformer.builder()
                .id(request.getId())
                .teamCode(request.getTeam())
                .strength(request.getStrength())
                .intelligence(request.getIntelligence())
                .speed(request.getSpeed())
                .endurance(request.getEndurance())
                .rank(request.getRank())
                .courage(request.getCourage())
                .firepower(request.getFirepower())
                .build();

        return transformerRepository.save(transformer);
    }
}
