package com.pirogsoft.mafia.states;

import com.pirogsoft.Tools.StateFactory;
import com.pirogsoft.mafia.MafiaGame;

import javax.servlet.ServletRequest;

/**
 * Дефолтное состояние системы. Система находится в этом состоянии, в случае, если в БД ничего нет про текущее состояние системы
 * Created by Andrey on 28.10.2016.
 */
public class StartState extends State{

    public StartState() {
        super();
    }

    @Override
    public String getViewFileName() {
        return "startState.jsp";
    }

    @Override
    protected void saveAndFinish(ServletRequest request, MafiaGame game) {
    }

    @Override
    protected State createNextState(ServletRequest request, MafiaGame game) {
        return StateFactory.getInstance().getStateByName("citySleeps");
    }


}
