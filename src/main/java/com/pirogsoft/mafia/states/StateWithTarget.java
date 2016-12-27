package com.pirogsoft.mafia.states;

import com.pirogsoft.mafia.Player;

/**
 * Created by Andrey on 22.10.2016.
 */
public abstract class StateWithTarget extends State {
    private Player target;

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }
}
