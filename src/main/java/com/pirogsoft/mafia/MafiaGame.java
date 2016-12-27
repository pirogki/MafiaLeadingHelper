package com.pirogsoft.mafia;

import com.pirogsoft.mafia.states.State;

import java.util.List;

/**
 * Created by Andrey on 22.10.2016.
 */
public class MafiaGame  {

    private int gameId;

    /**
     * Текущее состояние игры
     */
    State currentState;

    List<Player> players;

    RoleContainer roles;

    public int getGameId() {
        return gameId;
    }

    public MafiaGame(int gameId)
    {
        this.gameId = gameId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

}
