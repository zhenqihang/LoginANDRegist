package com.program.test;

import com.program.user.dao.UserDao;
import com.program.user.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testFindByUserName() {
        UserDao userDao = new UserDao();
        User user = userDao.findUserName("李四");
        System.out.println(user);
    }

    @Test
    public void testAdd() {
        UserDao userDao = new UserDao();

        User user = new User();
        user.setUsername("张三");
        user.setPassword("123");
        userDao.addUser(user);
    }
}
