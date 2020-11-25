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

    //登录功能
    public User login(User form) throws UserException {
        User user = userDao.findUserName(form.getUsername());
        if(user == null) throw new UserException("用户不存在");

        if(!form.getUsername().equals(user.getPassword())) throw new UserException("密码输入错误");
        return user;
    }
}
