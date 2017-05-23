
package com.dota.invoker.game;

import com.dota.invoker.AnswerCheck;
import com.dota.invoker.MainSpells;
import com.dota.invoker.RandomSkill;
import java.awt.Color;

import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import com.dota.invoker.sound.Sound;

/**
 *All the game mechanics are in this package.
 * @author Skaper
 */
public class Game {
    private short quasCount;
    private short wexCount;
    private short exortCount;
    private short counter;
    private int i = 1, j = 1;
    private String skillCheck;
    private StringBuilder sb;
    private JLabel spell;
    private JLabel firstReagent;
    private JLabel secondReagent;
    private JLabel thirdReagent;
    private JLabel spellLabel;
    private JLabel result;
    private JLabel finalResult;
    private long startTime;
    private long endTime;
    private boolean retry;
    private boolean allowInvoke;
    private int score;
    private int total;

    public Game() {
        this.total = 0;
        this.score = 0;
        this.allowInvoke = false;
        this.startTime = 0;
        this.endTime = 0;
        this.retry = false;
    }
    
    /**
     *Takes in all the game elements of Endless Classic mode.
     * @param spell
     * @param firstReagent
     * @param secondReagent
     * @param thirdReagent
     * @param spellLabel
     * @param result
     * @param finalResult
     */
    public Game(JLabel spell, JLabel firstReagent, 
            JLabel secondReagent, JLabel thirdReagent, JLabel spellLabel, 
            JLabel result, JLabel finalResult) {
        this.total = 0;
        this.score = 0;
        this.allowInvoke = false;
        this.startTime = 0;
        this.endTime = 0;
        this.retry = false;
        this.exortCount = 0;
        this.wexCount = 0;
        this.quasCount = 0;
        this.counter = 1;
        this.spell = spell;
        this.firstReagent = firstReagent;
        this.secondReagent = secondReagent;
        this.thirdReagent = thirdReagent;
        this.spellLabel = spellLabel;
        this.result = result;
        this.finalResult = finalResult;
    }
    
    public void letsPlayEndlessClassic(int keyCode) {
        
        if (keyCode == KeyEvent.VK_ENTER) {
            getDisplaySkill();
            this.spellLabel.setText(this.sb.toString());
            this.startTime = System.currentTimeMillis();
            finalResult.setText("Press S to Stop");
        }
        else if (keyCode == KeyEvent.VK_Q) {
            setPanelImage(panelImageDir(MainSpells.getSpell('Q')));
            this.quasCount++;
        }
        else if (keyCode == KeyEvent.VK_W) {
            setPanelImage(panelImageDir(MainSpells.getSpell('W')));
            this.wexCount++;
        }
        else if (keyCode == KeyEvent.VK_E) {
            setPanelImage(panelImageDir(MainSpells.getSpell('E')));
            this.exortCount++;
        }
        else if (keyCode == KeyEvent.VK_R && this.allowInvoke) {
            boolean result = AnswerCheck.validate(this.skillCheck, 
                    this.quasCount, this.wexCount, this.exortCount);
            
            this.total++;
            
            if (result) {
                Sound.playInvoke();
                this.result.setForeground(Color.GREEN);
                this.result.setText("Correct");
                this.score++;
            } 
            else if (!result) {
                this.result.setForeground(Color.RED);
                this.result.setText("Wrong");
            }
            
            setDefaultBorder();
            reset();
            getDisplaySkill();
            this.spellLabel.setText(this.sb.toString());
        }
        else if (keyCode == KeyEvent.VK_S) {
            this.endTime = System.currentTimeMillis();
            
            long millis = this.endTime - this.startTime;
            
            double temp = (double)this.score / this.total;
            double temp2 = (double) millis / 1000;
            System.out.println("before rounding: " +temp +" " +temp2);
            double roundOff = Math.round(temp * 100.0) / 100.0;
            double roundOff2 = Math.round(temp2 * 100.0) / 100.0;
            System.out.println("after rounding: " +roundOff +" " +roundOff2);
            
            String displayResult = "Score (higher is better): " 
                    +roundOff2 +" Accuracy: " 
                    +roundOff +"%.";
          
            
            finalResult.setText(displayResult);
        }
    }
    
    private void setPanelImage(String spellLocation) {
        try {
            switch (this.counter) {
                case 1:
                    this.result.setText(null);
                    if (this.retry) {
                        setDefaultBorder();
                        this.allowInvoke = false;
                        reset();
                    }
                    this.firstReagent.setIcon(new javax.swing.ImageIcon(getClass().getResource(spellLocation)));
                    this.counter = 2;
                    break;
                case 2:
                    this.secondReagent.setIcon(new javax.swing.ImageIcon(getClass().getResource(spellLocation)));
                    this.counter = 3;
                    break;
                case 3:
                    this.thirdReagent.setIcon(new javax.swing.ImageIcon(getClass().getResource(spellLocation)));
                    this.counter = 1;
                    this.retry = true;
                    this.allowInvoke = true;
                    break;
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void setDefaultBorder() {
        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        this.firstReagent.setIcon(null);
        this.secondReagent.setIcon(null);
        this.thirdReagent.setIcon(null);
        if (!this.retry)
            spell.setIcon(null);
        
        this.firstReagent.setBorder(border);
        this.secondReagent.setBorder(border);
        this.thirdReagent.setBorder(border);
        spell.setBorder(border);
    }
    
    public boolean getAnswer() {
        return AnswerCheck.validate(this.skillCheck, quasCount, wexCount, wexCount);
    }
    
    public void getDisplaySkill() {
        
        try {
            RandomSkill random = new RandomSkill();
            String temp = random.getRandomSkill(true);
            this.skillCheck = temp;
            
            this.sb = new StringBuilder();
            
            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) != '_')
                    this.sb.append(temp.charAt(i));
                else
                    this.sb.append(' ');
            }
            
            String s = "/com/dota/invoker/spell_icons/" 
                    + temp + ".png";
            
            this.spell.setIcon(new javax.swing.ImageIcon(getClass().getResource(s)));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    private String getSkillName(int keyCode) {
        MainSpells spell = new MainSpells();
        String spellName = null;
        
        switch (keyCode) {
            case KeyEvent.VK_Q:
                quasCount++;
                spellName = spell.getSpell('Q');
                break;
            case KeyEvent.VK_W:
                wexCount++;
                spellName = spell.getSpell('W');
                break;
            case KeyEvent.VK_E:
                exortCount++;
                spellName = spell.getSpell('E');
                break;
            case KeyEvent.VK_R:
                break;
        }
        
        return spellName;
    }
    
    private String panelImageDir(String tempSpell) {
        String spellLocation = null;
        
        spellLocation = "/com/dota/invoker/spell_icons/" +tempSpell +".png";
        
        return spellLocation;
    }
    
    private void reset() {
        this.quasCount = 0;
        this.wexCount = 0;
        this.exortCount = 0;
        this.counter = 1;
        this.retry = false;
        this.allowInvoke = false;
    }
}
