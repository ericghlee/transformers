package com.eric.transformers.infrastructure.http.presentation;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransformerRequest {

    @NotNull
    private String id;

    @NotNull
    private String team;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer strength;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer intelligence;

    @NotNull
    @Min(1)
    @Max(10)

    private Integer speed;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer endurance;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer rank;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer courage;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer firepower;
}
