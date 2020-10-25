package com.eric.transformers.application.util;

import com.eric.transformers.domain.BattleResult;
import com.eric.transformers.domain.Transformer;

public class BattleUtil {

    public static BattleResult battle(Transformer autobot, Transformer decepticon) {
        if ("Optimus Prime".equals(autobot.getId()) || "Predaking".equals(decepticon.getId())) {
            if ("Optimus Prime".equals(autobot.getId()) && "Predaking".equals(decepticon.getId())) {
                return BattleResult.END;
            } else if ("Optimus Prime".equals(autobot.getId())) {
                return BattleResult.AUTOBOTS;
            } else {
                return BattleResult.DECEPTICONS;
            }
        }

        if (isStatusWin(autobot, decepticon)) {
            return BattleResult.AUTOBOTS;
        } else if (isStatusWin(decepticon, autobot)) {
            return BattleResult.DECEPTICONS;
        } else {
            return skillBattle(autobot, decepticon);
        }
    }

    private static boolean isStatusWin(Transformer first, Transformer second) {
        return (first.getCourage() - second.getCourage() >= 4 && first.getStrength() - second.getStrength() >= 3) ||
                (first.getSkill() - second.getSkill() >= 3);
    }

    private static BattleResult skillBattle(Transformer autobot, Transformer decepticon) {
        if (autobot.getRating() > decepticon.getRating()) {
            return BattleResult.AUTOBOTS;
        } else if (decepticon.getRating() > autobot.getRating()) {
            return BattleResult.DECEPTICONS;
        } else {
            return BattleResult.TIE;
        }
    }
}
