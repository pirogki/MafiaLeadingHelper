package com.pirogsoft.database;

import com.pirogsoft.exceptions.NoRecordException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrey on 30.10.2016.
 */
public class UpdateQuery implements IListGetter{

    public UpdateQuery()
    {

    }

    public String getQuery() {
        return null;
    }

    public List getResultList(ResultSet rs) throws SQLException, NoRecordException {
        return null;
    }
}
