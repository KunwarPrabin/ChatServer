/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.chatserver.dao.impl;

import com.leapfrog.chatserver.dao.UserDAO;
import com.leapfrog.chatserver.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OWNER
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAll() {
        List<User> users=new ArrayList<>();
        users.add(new User(1,"aswin","aswin"));
        users.add(new User(2,"dixanta","dixanta"));
        users.add(new User(3,"sudama","sudama"));
        return users;
    }

    @Override
    public User login(String userName, String password) {
        for(User u:getAll()){
            if(u.getUserName().equals(userName) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }
    
}
