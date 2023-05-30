package com.player;
import com.player.Player;

public class AiPlayer implements Player {

    private final String name = "Hal";
    private final String mark = "X";




     @Override
    public String getPlayer() {
        return this.name;
    }


    @Override
    public String setMark() {
        return this.mark;
    }

    // return this as TRUE when its hals turn and FALSE when its LiveActionPlayers turn to input coordinates
    @Override
    public boolean halsTurn() {
        return true;
    }
}