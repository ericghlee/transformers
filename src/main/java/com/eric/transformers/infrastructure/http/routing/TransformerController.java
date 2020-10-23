package com.eric.transformers.infrastructure.http.routing;

import com.eric.transformers.application.CreateTransformerUseCase;
import com.eric.transformers.infrastructure.http.presentation.CreateTransformerRequest;
import com.eric.transformers.infrastructure.http.presentation.CreateTransformerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransformerController {

    private final CreateTransformerUseCase createTransformerUseCase;

    public TransformerController(CreateTransformerUseCase createTransformerUseCase) {
        this.createTransformerUseCase = createTransformerUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateTransformerResponse> createTransformer(
            @Valid @RequestBody CreateTransformerRequest request) {

        createTransformerUseCase.execute(request);

        return ResponseEntity.ok()
                .body(CreateTransformerResponse.builder()
                        .message("Transformer created successfully")
                        .build());
    }
}
