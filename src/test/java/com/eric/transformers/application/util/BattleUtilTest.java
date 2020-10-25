package com.eric.transformers.application.util;

import com.eric.transformers.domain.BattleResult;
import com.eric.transformers.domain.Transformer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BattleUtilTest {

    @Test
    public void testBattle_optimus_prime() {
        Transformer autobot = Transformer.builder().id("Optimus Prime").build();
        Transformer decepticon = Transformer.builder().id("Soundwave").build();

        assertEquals(BattleResult.AUTOBOTS, BattleUtil.battle(autobot, decepticon));
    }

    @Test
    public void testBattle_predaking() {
        Transformer autobot = Transformer.builder().id("Soundwave").build();
        Transformer decepticon = Transformer.builder().id("Predaking").build();

        assertEquals(BattleResult.DECEPTICONS, BattleUtil.battle(autobot, decepticon));
    }

    @Test
    public void testBattle_end() {
        Transformer autobot = Transformer.builder().id("Optimus Prime").build();
        Transformer decepticon = Transformer.builder().id("Predaking").build();

        assertEquals(BattleResult.END, BattleUtil.battle(autobot, decepticon));
    }

    @Test
    public void testBattle_courage_strengh_win() {
        Transformer autobot = Transformer.builder()
                .id("Soundwave")
                .courage(10)
                .strength(10)
                .build();

        Transformer decepticon = Transformer.builder()
                .id("Decepticon")
                .courage(6)
                .strength(7)
                .build();

        assertEquals(BattleResult.AUTOBOTS, BattleUtil.battle(autobot, decepticon));
    }

    @Test
    public void testBattle_skill_win() {
        Transformer autobot = Transformer.builder()
                .id("Soundwave")
                .courage(4)
                .strength(8)
                .skill(7)
                .build();

        Transformer decepticon = Transformer.builder()
                .id("Decepticon")
                .courage(6)
                .strength(7)
                .skill(10)
                .build();

        assertEquals(BattleResult.DECEPTICONS, BattleUtil.battle(autobot, decepticon));
    }

    @Test
    public void testBattle_rating_win() {
        Transformer autobot = Transformer.builder()
                .id("Soundwave")
                .courage(4)
                .strength(8)
                .skill(7)
                .strength(8)
                .intelligence(10)
                .speed(2)
                .endurance(6)
                .firepower(6)
                .build();

        Transformer decepticon = Transformer.builder()
                .id("Decepticon")
                .courage(6)
                .strength(7)
                .skill(7)
                .strength(8)
                .intelligence(9)
                .speed(2)
                .endurance(6)
                .firepower(6)
                .build();

        assertEquals(BattleResult.AUTOBOTS, BattleUtil.battle(autobot, decepticon));
    }

    @Test
    public void testBattle_rating_tie() {
        Transformer autobot = Transformer.builder()
                .id("Soundwave")
                .courage(4)
                .strength(8)
                .skill(7)
                .strength(8)
                .intelligence(10)
                .speed(2)
                .endurance(6)
                .firepower(6)
                .build();

        Transformer decepticon = Transformer.builder()
                .id("Decepticon")
                .courage(6)
                .strength(7)
                .skill(7)
                .strength(8)
                .intelligence(10)
                .speed(2)
                .endurance(6)
                .firepower(6)
                .build();

        assertEquals(BattleResult.TIE, BattleUtil.battle(autobot, decepticon));
    }
}