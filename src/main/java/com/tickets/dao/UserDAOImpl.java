package com.tickets.dao;

import com.tickets.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Leon on 2016-08-04.
 */
public class UserDAOImpl implements UserDAO{

    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(String username){
        String sql = "SELECT * FROM users u WHERE u.username ='" + username + "'";

        return jdbcTemplate.queryForObject(sql,new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(rs.getInt("USER_ID"));
                user.setUserName(rs.getString("username"));
                user.setUserPass(rs.getString("password"));
                return user;
            }
        });


    }

}
