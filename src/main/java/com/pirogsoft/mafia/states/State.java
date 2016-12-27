package com.pirogsoft.mafia.states;

import com.pirogsoft.Tools.StateFactory;
import com.pirogsoft.database.OneValueGetter;
import com.pirogsoft.mafia.MafiaGame;
import com.pirogsoft.mafia.Player;
import com.pirogsoft.sql.queryBuilder;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * Created by Andrey on 22.10.2016.
 */

public class State {

    public State (MafiaGame game)
    {
        this.game = game;
       // this.stateInGameId = stateInGameId;
        //TODO: По умолчанию только ставим currentState = true и назначаем id для связи с звписью в бд, забираем hint из бд
    }

    /**
     * id предыдущего состояния системы в рамках этой игры
     */
    private int previousStateInGameId;

    /**
     * id следующего типового состояния системы
     */
    private int nextStateId;

    /**
     * Фраза, которую нужно сообщить игрокам
     */
    private String phrase;

    /**
     * Подсказка для ведущего
     */
    private String tipLeading;

    /**
     * id состояния
     */
    private int stateInGameId;

    private MafiaGame game;

    protected void saveAndFinish(ServletRequest request, MafiaGame game)
    {
        //TODO: По умолчанию только ставим currentState = false;
    }


    protected State createNextState(ServletRequest request, MafiaGame game)
    {

        //TODO: По умолчанию обращаемся к бд, получаем nextStateId; После этого - возвращаем по id новый объект, с помощью класса StateFactory
        return null;
    }

    public final State getNextState(ServletRequest request, MafiaGame game)
    {
        if(!checkParams(request))
            return this;
        saveAndFinish(request, game);
        createNextStateInDb(request, game);
        State newState = StateFactory.getInstance().getCurrentState(game);
        newState.setPreviousStateInGameId(stateInGameId);
        return newState;
    }

    private boolean checkParams(ServletRequest request) {
        return true;
    }

    private void createNextStateInDb(ServletRequest request, MafiaGame game) {

    }

    public static void createState()
    {

    }
    public List<Player> getPlayers()
    {
        return game.getPlayers();
    }


    public String getPhrase(List<String> args)
    {
        String resultPhrase = phrase;
        for (int i=0; i<args.size(); i++)
        {
            String arg = args.get(i);
            resultPhrase = resultPhrase.replaceAll("\\$" + String.valueOf(i - 1), arg);
        }
        return resultPhrase;
    }

    public String getPhrase()
    {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public int getPreviousStateInGameId() {
        return previousStateInGameId;

        //TODO: UPDATE TO DB
    }

    public void setPreviousStateInGameId(int previousStateInGameId)
    {
        this.previousStateInGameId = previousStateInGameId;
    }

    /**
     * Метод определяет название необходимой jsp-страницы
     * @return имя страницы
     */
    public String getViewFileName()
    {
        OneValueGetter viewGetter = new OneValueGetter(queryBuilder.getInstance().viewByStateInGame(stateInGameId));
        return viewGetter.getResultValue();
    }

    /**
     * Откатывает текущее состояние (undo)
     */
    public void revert()
    {

    }
}
