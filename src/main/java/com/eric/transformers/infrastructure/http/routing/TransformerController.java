package com.eric.transformers.infrastructure.http.routing;

import com.eric.transformers.application.*;
import com.eric.transformers.domain.BattleStats;
import com.eric.transformers.domain.Transformer;
import com.eric.transformers.infrastructure.http.presentation.request.TransformerRequest;
import com.eric.transformers.infrastructure.http.presentation.response.BattleResponse;
import com.eric.transformers.infrastructure.http.presentation.response.DefaultResponse;
import com.eric.transformers.infrastructure.http.presentation.ResponseMessages;
import com.eric.transformers.infrastructure.http.presentation.response.ListTransformersResponse;
import com.eric.transformers.infrastructure.http.presentation.response.TransformerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping(path = "/api")
@RestController
public class TransformerController {

    private final CreateTransformerUseCase createTransformerUseCase;

    private final UpdateTransformerUseCase updateTransformerUseCase;

    private final ListTransformerUseCase listTransformerUseCase;

    private final DeleteTransformerUseCase deleteTransformerUseCase;

    private final BattleUseCase battleUseCase;

    public TransformerController(CreateTransformerUseCase createTransformerUseCase,
                                 UpdateTransformerUseCase updateTransformerUseCase,
                                 ListTransformerUseCase listTransformerUseCase,
                                 DeleteTransformerUseCase deleteTransformerUseCase,
                                 BattleUseCase battleUseCase) {
        this.createTransformerUseCase = createTransformerUseCase;
        this.updateTransformerUseCase = updateTransformerUseCase;
        this.listTransformerUseCase = listTransformerUseCase;
        this.deleteTransformerUseCase = deleteTransformerUseCase;
        this.battleUseCase = battleUseCase;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<DefaultResponse> createTransformer(
            @Valid @RequestBody TransformerRequest request) {

        try {
            createTransformerUseCase.execute(request);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(DefaultResponse.builder()
                            .error(true)
                            .message(String.format(ResponseMessages.ALREADY_EXISTS, request.getId()))
                            .build());
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(DefaultResponse.builder()
                        .message(ResponseMessages.CREATED_MESSAGE)
                        .build());
    }

    @PutMapping(path = "/update")
    public ResponseEntity<DefaultResponse> updateTransformer(
            @Valid @RequestBody TransformerRequest request) {

        try {
            updateTransformerUseCase.execute(request);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(DefaultResponse.builder()
                            .error(true)
                            .message(String.format(ResponseMessages.DOES_NOT_EXISTS, request.getId()))
                            .build());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(DefaultResponse.builder()
                        .message(ResponseMessages.UPDATED_MESSAGE)
                        .build());
    }

    @GetMapping(path = "/list")
    public ResponseEntity<ListTransformersResponse> listTransformers() {

        Set<TransformerResponse> transformers = listTransformerUseCase.execute().stream()
            .map(TransformerResponse::new)
            .collect(Collectors.toSet());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ListTransformersResponse.builder()
                        .transformers(transformers)
                        .build());
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<DefaultResponse> deleteTransformer(@PathVariable("id") String id) {

        try {
            deleteTransformerUseCase.execute(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(DefaultResponse.builder()
                            .error(true)
                            .message(String.format(ResponseMessages.DOES_NOT_EXISTS, id))
                            .build());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(DefaultResponse.builder()
                        .message(ResponseMessages.DELETE_MESSAGE)
                        .build());
    }

    @PostMapping(path = "/battle")
    public ResponseEntity<BattleResponse> battle() {

        BattleStats stats = battleUseCase.execute();

        BattleResponse response = BattleResponse.builder()
                .winner(stats.getResult().name())
                .ties(stats.getTies())
                .autobotsWins(stats.getAutobotsWins())
                .decepticonsWins(stats.getDecepticonsWins())
                .totalBattles(stats.getTotalBattles())
                .autobotsSurvivor(stats.getAutobotsSurvivors().stream().map(Transformer::getId).collect(Collectors.toSet()))
                .decepticonsSurvivor(stats.getDecepticonSurvivors().stream().map(Transformer::getId).collect(Collectors.toSet()))
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
