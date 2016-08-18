package com.tickets.dao;

import com.tickets.model.ChartKeyValue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz.homik on 2016-08-18.
 */
public class ChartsDAOImpl implements ChartsDAO {

    private JdbcTemplate jdbcTemplate;
//    private List<ChartKeyValue> pieDataList =  new List<ChartKeyValue>();
    public ChartsDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ChartKeyValue> getPieChartData() {
        String sql = "SELECT STATUS,COUNT(ID) as VAL FROM tickets GROUP BY STATUS";
        List<ChartKeyValue> pieDataList = jdbcTemplate.query(sql, new RowMapper<ChartKeyValue>() {

            @Override
            public ChartKeyValue mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChartKeyValue key = new ChartKeyValue();
                key.setKey(rs.getString("STATUS"));
                key.setValue(rs.getString("VAL"));

                return key;
            }

        });



        return pieDataList;
    }

}
