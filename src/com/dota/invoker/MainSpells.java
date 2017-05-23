
package com.dota.invoker;

public class MainSpells {
    private static final String QUAS = "Quas";
    private static final String WEX = "Wex";
    private static final String EXORT = "Exort";
    
    public static String getSpell(char spell) {
        switch (spell) {
            case 'Q': 
                return QUAS;
            case 'W': 
                return WEX;
            case 'E': 
                return EXORT;
        }
        
        return null;
    }
}
