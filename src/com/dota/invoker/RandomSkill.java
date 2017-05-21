
package com.dota.invoker;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Generates a random skill.
 * @author atifk
 */
public class RandomSkill {
    public static final String EMP = "EMP";
    public static final String TORNADO = "Tornado";
    public static final String ALACRITY = "Alacrity";
    public static final String GHOST_WALK = "Ghost_Walk";
    public static final String DEAFENING_BLAST = "Deafening_Blast";
    public static final String CHAOS_METEOR = "Chaos_Meteor";
    public static final String COLD_SNAP = "Cold_Snap";
    public static final String ICE_WALL = "Ice_Wall";
    public static final String FORGE_SPIRIT = "Forge_Spirit";
    public static final String SUN_STRIKE = "Sun_Strike";
    public static List<Integer> skillNumber;
    
    public RandomSkill() {
        skillNumber = new ArrayList();
    }
    
    /**
     * Clears the list containing skill number.
     */
    public void clearList() {
        this.skillNumber.clear();
    }
    
    /**
     * Get a random skill name.
     * @param repeatSkill Set false if you don't want repeated skills. 
     * @return A randomly chosen skill.
     */
    public String getRandomSkill(boolean repeatSkill) {
        Random random = new Random();
        int i = 0;
        
        if (repeatSkill) {
            i = random.nextInt(10) + 1;
            return getSkillName(i);
        }
        else {
            do {
                i = random.nextInt(10) + 1;
                if (!this.skillNumber.contains(i)) {
                    this.skillNumber.add(i);
                    return getSkillName(i);
                }
            } while (true);
        }
    }
    
    /**
     * Get the skill based on the random number passed.
     * @param i
     * @return Name of skill.
     */
    private String getSkillName(int i) {
        switch (i) {
            case 1:
                return this.ALACRITY;
            case 2:
                return this.CHAOS_METEOR;
            case 3:
                return this.COLD_SNAP;
            case 4:
                return this.DEAFENING_BLAST;
            case 5:
                return this.EMP;
            case 6:
                return this.FORGE_SPIRIT;
            case 7:
                return this.GHOST_WALK;
            case 8:
                return this.ICE_WALL;
            case 9:
                return this.SUN_STRIKE;
            case 10:
                return this.TORNADO;
        }
        
        return null;
    }
}
