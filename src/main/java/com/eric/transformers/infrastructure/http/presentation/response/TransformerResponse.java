package com.eric.transformers.infrastructure.http.presentation.response;


import com.eric.transformers.domain.Transformer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransformerResponse {

    private String id;

    private String team;

    private Integer strength;

    private Integer intelligence;

    private Integer speed;

    private Integer endurance;

    private Integer rank;

    private Integer courage;

    private Integer firepower;

    public TransformerResponse(Transformer transformer) {
        this.id = transformer.getId();
        this.team = transformer.getTeamCode();
        this.strength = transformer.getStrength();
        this.intelligence = transformer.getIntelligence();
        this.speed = transformer.getSpeed();
        this.endurance = transformer.getEndurance();
        this.rank = transformer.getRank();
        this.courage = transformer.getCourage();
        this.firepower = transformer.getFirepower();
    }

}
