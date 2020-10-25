package com.eric.transformers.infrastructure.http.routing;

import com.eric.transformers.application.CreateTransformerUseCase;
import com.eric.transformers.application.DeleteTransformerUseCase;
import com.eric.transformers.application.ListTransformerUseCase;
import com.eric.transformers.application.UpdateTransformerUseCase;
import com.eric.transformers.fixture.TransformerFixture;
import com.eric.transformers.infrastructure.http.presentation.request.TransformerRequest;
import com.eric.transformers.infrastructure.http.presentation.response.DefaultResponse;
import com.eric.transformers.infrastructure.http.presentation.ResponseMessages;
import com.eric.transformers.infrastructure.http.presentation.response.ListTransformersResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransformerControllerTest {

    @Mock
    private CreateTransformerUseCase createTransformerUseCase;

    @Mock
    private UpdateTransformerUseCase updateTransformerUseCase;

    @Mock
    private ListTransformerUseCase listTransformerUseCase;

    @Mock
    private DeleteTransformerUseCase deleteTransformerUseCase;

    @InjectMocks
    private TransformerController transformerController;

    @Test
    public void testCreateTransformer() {
        TransformerRequest request = TransformerFixture.getSoundwaveRequest();

        ResponseEntity<DefaultResponse> response = transformerController.createTransformer(request);

        verify(createTransformerUseCase).execute(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseMessages.CREATED_MESSAGE, response.getBody().getMessage());
    }

    @Test
    public void testCreateTransformer_already_exists() {
        TransformerRequest request = TransformerFixture.getSoundwaveRequest();
        when(createTransformerUseCase.execute(request)).thenThrow(new IllegalArgumentException());

        ResponseEntity<DefaultResponse> response = transformerController.createTransformer(request);

        verify(createTransformerUseCase).execute(request);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertTrue(response.getBody().getMessage().contains(request.getId()));
    }

    @Test
    public void testUpdateTransformer() {
        TransformerRequest request = TransformerFixture.getSoundwaveRequest();

        ResponseEntity<DefaultResponse> response = transformerController.updateTransformer(request);

        verify(updateTransformerUseCase).execute(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseMessages.UPDATED_MESSAGE, response.getBody().getMessage());
    }

    @Test
    public void testUpdateTransformer_does_not_exists() {
        TransformerRequest request = TransformerFixture.getSoundwaveRequest();
        when(updateTransformerUseCase.execute(request)).thenThrow(new IllegalArgumentException());

        ResponseEntity<DefaultResponse> response = transformerController.updateTransformer(request);

        verify(updateTransformerUseCase).execute(request);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertTrue(response.getBody().getMessage().contains(request.getId()));
    }


    @Test
    public void getListTransformer() {
        ResponseEntity<ListTransformersResponse> response = transformerController.listTransformers();

        verify(listTransformerUseCase).execute();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getTransformers().isEmpty());
    }

    @Test
    public void deleteTransformer() {
        String id = "transformer";

        ResponseEntity<DefaultResponse> response = transformerController.deleteTransformer(id);

        verify(deleteTransformerUseCase).execute(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseMessages.DELETE_MESSAGE, response.getBody().getMessage());
    }

    @Test
    public void deleteTransformer_does_not_exists() {
        String id = "transformer";
        doThrow(new IllegalArgumentException()).when(deleteTransformerUseCase).execute(id);

        ResponseEntity<DefaultResponse> response = transformerController.deleteTransformer(id);

        verify(deleteTransformerUseCase).execute(id);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertTrue(response.getBody().getMessage().contains(id));
    }
}