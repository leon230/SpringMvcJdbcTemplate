package com.tickets.dao;

import com.tickets.model.User;
import java.util.List;

/**
 * Created by Leon on 2016-08-04.
 */
public interface UserDAO {

    public List<User> listuser(String userName);

}
