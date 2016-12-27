/**
 * 
 */
package com.pirogsoft.database;

import com.pirogsoft.exceptions.NoRecordException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Andrey
 *
 */
public interface IListGetter<T> {
	public String getQuery();
	public List<T> getResultList(ResultSet rs) throws SQLException, NoRecordException;
}
