package com.eric.transformers.infrastructure.http.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListTransformersResponse {

    Set<TransformerResponse> transformers;
}
