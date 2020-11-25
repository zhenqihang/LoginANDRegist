package com.program.user.service;

import com.program.user.dao.UserDao;
import com.program.user.domain.User;

public class UserService {
    private UserDao userDao= new UserDao();

    public void register(User user) throws UserException {
        User findUser = userDao.findUserName(user.getUsername());
        if(findUser != null)    throw new UserException("用户名已存在");

        userDao.addUser(user);
    }
}
