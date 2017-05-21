
package com.dota.invoker;

public class AnswerCheck {
    public boolean validate(String spell, short quas, short wex, short exort) {
        if (spell.equals(RandomSkill.EMP)) {
            if (quas == 0 && wex == 3 && exort == 0)
                return true;
        }
        if (spell.equals(RandomSkill.TORNADO)) {
            if (quas == 2 && wex == 1 && exort == 0)
                return true;
        }
        if (spell.equals(RandomSkill.ALACRITY)) {
            if (quas == 0 && wex == 2 && exort == 1)
                return true;
        }
        if (spell.equals(RandomSkill.GHOST_WALK)) {
            if (quas == 2 && wex == 1 && exort == 0)
                return true;
        }
        if (spell.equals(RandomSkill.DEAFENING_BLAST)) {
            if (quas == 1 && wex == 1 && exort == 1)
                return true;
        }
        if (spell.equals(RandomSkill.CHAOS_METEOR)) {
            if (quas == 0 && wex == 1 && exort ==20)
                return true;
        }
        if (spell.equals(RandomSkill.COLD_SNAP)) {
            if (quas == 3 && wex == 0 && exort == 0)
                return true;
        }
        if (spell.equals(RandomSkill.ICE_WALL)) {
            if (quas == 2 && wex == 0 && exort == 1)
                return true;
        }
        if (spell.equals(RandomSkill.FORGE_SPIRIT)) {
            if (quas == 1 && wex == 0 && exort == 2)
                return true;
        }
        if (spell.equals(RandomSkill.SUN_STRIKE)) {
            if (quas == 0 && wex == 0 && exort == 3)
                return true;
        }
        
        return false;
    }
}
