package com.pirogsoft.Tools;

import com.pirogsoft.database.Connector;
import com.pirogsoft.database.IListGetter;
import com.pirogsoft.database.OneValueGetter;
import com.pirogsoft.mafia.MafiaGame;
import com.pirogsoft.mafia.states.State;
import com.pirogsoft.sql.queryBuilder;

/**
 * Created by Andrey on 28.10.2016.
 */
public class StateFactory {

    private static final String STATE_TYPE_BY_GAME_ID = "SELECT state_types.name AS 'value' FROM states_in_games INNER JOIN states ON states_in_games.state_id = states.id INNER JOIN state_types ON states.state_type_id = state_types.id WHERE states_in_games.is_current = 1 AND states_in_games = ";

    private static StateFactory ourInstance = new StateFactory();

    public static StateFactory getInstance() {
        return ourInstance;
    }

    private StateFactory() {
    }

//    public State getStateByName(String stateName)
//    {
//        OneValueGetter viewGetter = new OneValueGetter(queryBuilder.getInstance().stateTypeByState(stateName));
//        return getStateByType(viewGetter.getResultValue());
//    }

    public State getStateByType(String stateTypeName, MafiaGame game)
    {
        if(stateTypeName == "citySleeps")
        {

        }
        //TODO: Исключение
        //throw noClassFindExeption
        return null;
    }

    public State getCurrentState(MafiaGame game) {
        //1)Запрос в бд - ищем state (не state_in_game) по id игры и не закрытой state - получаем stateTypeName
        OneValueGetter stageTypeGetter = new OneValueGetter(STATE_TYPE_BY_GAME_ID + game.getGameId());
        String stageType = stageTypeGetter.getResultValue();
        return getStateByType(stageType, game);
    }
}
