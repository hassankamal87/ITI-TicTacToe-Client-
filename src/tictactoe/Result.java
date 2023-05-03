/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import tictactoe.utility.GameLevel;
import tictactoe.utility.GameMode;
import tictactoe.utility.MatchStatus;
import tictactoe.utility.PlayerSympol;

/**
 *
 * @author Mohamed Adel
 */
public class Result {
    
    private GameMode mode;
    private GameLevel level;
    private PlayerSympol sympol;
    private MatchStatus matchStatus;
    private int leftSideScore;
    private int rightSideScore;

    public Result(GameMode mode, GameLevel level, PlayerSympol sympol, MatchStatus matchStatus, int leftSideScore, int rightSideScore) {
        this.mode = mode;
        this.level = level;
        this.sympol = sympol;
        this.matchStatus = matchStatus;
        this.leftSideScore = leftSideScore;
        this.rightSideScore = rightSideScore;
    }


    

    public GameMode getMode() {
        return mode;
    }

    public GameLevel getLevel() {
        return level;
    }

    public PlayerSympol getSympol() {
        return sympol;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public int getLeftSideScore() {
        return leftSideScore;
    }

    public int getRightSideScore() {
        return rightSideScore;
    }

    @Override
    public String toString() {
        return "Result{" + "mode=" + mode + ", level=" + level + ", sympol=" + sympol + ", matchStatus=" + matchStatus + ", leftSideScore=" + leftSideScore + ", rightSideScore=" + rightSideScore + '}';
    }

    
     
    
}
