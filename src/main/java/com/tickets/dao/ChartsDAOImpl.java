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
 * Data for priority values simple chart
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

}
