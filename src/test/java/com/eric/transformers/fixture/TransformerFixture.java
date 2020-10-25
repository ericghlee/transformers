package com.eric.transformers.fixture;

import com.eric.transformers.infrastructure.http.presentation.request.TransformerRequest;

public class TransformerFixture {

    public static TransformerRequest getSoundwaveRequest() {
        return TransformerRequest.builder()
                .id("Soundwave")
                .team("D")
                .strength(8)
                .intelligence(9)
                .speed(2)
                .endurance(6)
                .rank(7)
                .courage(5)
                .firepower(6)
                .skill(10)
                .build();
    }

    public static TransformerRequest getBluestreakRequest() {
        return TransformerRequest.builder()
                .id("Bluestreak")
                .team("A")
                .strength(6)
                .intelligence(6)
                .speed(7)
                .endurance(9)
                .rank(5)
                .courage(2)
                .firepower(9)
                .skill(7)
                .build();
    }

    public static TransformerRequest getHubcapRequest() {
        return TransformerRequest.builder()
                .id("Hubcap")
                .team("A")
                .strength(4)
                .intelligence(4)
                .speed(4)
                .endurance(4)
                .rank(4)
                .courage(4)
                .firepower(4)
                .skill(4)
                .build();
    }
}
