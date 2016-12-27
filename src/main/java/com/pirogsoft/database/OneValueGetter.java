package com.pirogsoft.database;

import com.pirogsoft.exceptions.NoRecordException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 27.10.2016.
 */
public class OneValueGetter implements IListGetter<String> {

    private String query;

    public OneValueGetter(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public List<String> getResultList(ResultSet rs) throws SQLException, NoRecordException {

            ArrayList<String> result = new ArrayList<String>();
            if(rs.next())
            {
                result.add("value");
            }
            else
            {
                throw new NoRecordException("No records for query " + getQuery());
            }
            return result;
    }

    public String getResultValue()
    {
        List<String> dbResponse = Connector.getInstance().getResult(this);
        return dbResponse.get(0);
    }
}
