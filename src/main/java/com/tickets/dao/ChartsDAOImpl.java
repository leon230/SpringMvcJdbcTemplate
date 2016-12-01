package com.tickets.dao;

import com.tickets.model.ChartKeyValue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lukasz.homik on 2016-08-18.
 */
public class ChartsDAOImpl implements ChartsDAO {

    private JdbcTemplate jdbcTemplate;
    public ChartsDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

/**
 * Chart 1 fields retrieval
 */

    @Override
    public List<ChartKeyValue> getProgressData() {
        String sql = "SELECT STATUS,COUNT(ID) as VAL FROM tickets GROUP BY STATUS";
        List<ChartKeyValue> DataList = jdbcTemplate.query(sql, new RowMapper<ChartKeyValue>() {
/**
 * Data for status values simple chart
 */
            @Override
            public ChartKeyValue mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChartKeyValue key = new ChartKeyValue();
                key.setKey(rs.getString("STATUS"));
                key.setValue(rs.getString("VAL"));

                return key;
            }
        });
        return DataList;
    }
/**
 * Chart 2 fields retrieval
 */
    @Override
    public List<ChartKeyValue> getPriorityData() {
        String sql = "SELECT PRIORITY,COUNT(ID) as VAL FROM tickets GROUP BY PRIORITY";
        List<ChartKeyValue> DataList = jdbcTemplate.query(sql, new RowMapper<ChartKeyValue>() {

            @Override
            public ChartKeyValue mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChartKeyValue key = new ChartKeyValue();
                key.setKey(rs.getString("PRIORITY"));
                key.setValue(rs.getString("VAL"));

                return key;
            }
        });
        return DataList;
    }
/**
 * Chart 3 fields retrieval
 */
    @Override
    public List<ChartKeyValue> getPrioritySolveData() {
        String sql = "SELECT distinct concat(year(t.OPEN_DATE),month(t.OPEN_DATE)) as c_date" +
                ",(select count(id) from tickets where priority = 'High' AND concat(year(t.OPEN_DATE),month(t.OPEN_DATE)) = concat(year(OPEN_DATE),month(OPEN_DATE))) as c_high" +
                ",(select count(id) from tickets where priority = 'Medium' AND concat(year(t.OPEN_DATE),month(t.OPEN_DATE)) = concat(year(OPEN_DATE),month(OPEN_DATE))) as c_med" +
                ",(select count(id) from tickets where priority = 'Low' AND concat(year(t.OPEN_DATE),month(t.OPEN_DATE)) = concat(year(OPEN_DATE),month(OPEN_DATE))) as c_low " +
                "from tickets t";
        List<ChartKeyValue> DataList = jdbcTemplate.query(sql, new RowMapper<ChartKeyValue>() {

            @Override
            public ChartKeyValue mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChartKeyValue key = new ChartKeyValue();
                key.setKey(rs.getString("c_date"));
                key.setValue(rs.getString("c_high"));
                key.setValue2(rs.getString("c_med"));
                key.setValue3(rs.getString("c_low"));

                return key;
            }
        });
        return DataList;
    }

}
