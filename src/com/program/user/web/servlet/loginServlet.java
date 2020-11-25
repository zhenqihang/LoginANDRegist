package com.program.user.web.servlet;

import cn.itcast.commons.CommonUtils;
import com.program.user.domain.User;
import com.program.user.service.UserException;
import com.program.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        UserService userService = new UserService();

        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        try {
            User user = userService.login(form);
            request.getSession().setAttribute("sessionUser",user);
            response.sendRedirect(request.getContextPath()+"/user/index.jsp");
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("user",form);
            request.getRequestDispatcher("/user/login.jsp").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
