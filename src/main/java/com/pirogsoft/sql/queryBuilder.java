package com.pirogsoft.sql;

/**
 * Created by Andrey on 29.10.2016.
 */
public class queryBuilder {
    private static queryBuilder ourInstance = new queryBuilder();

    public static queryBuilder getInstance() {
        return ourInstance;
    }

    private queryBuilder() {
    }

    public String stateTypeByState (String stateName)
    {
        return "SELECT states_types.name AS 'value' FROM states INNER JOIN states_types ON states.state_type_id = state_type.id WHERE state.name" + stateName ;
    }

    public String viewByStateInGame(int stateInGameId)
    {
        return "SELECT view_file_name AS 'value' FROM states_in_games INNER JOIN states ON states_in_games.state_id = states.id WHERE states_in_games.id =" + stateInGameId;
    }
}
