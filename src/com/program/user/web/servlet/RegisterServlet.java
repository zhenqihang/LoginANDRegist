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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        UserService userService = new UserService();

        //封装表单数据
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);



        //表单校验
        Map<String,String> errors = new HashMap<>();
        String username = form.getUsername();
        String pwd = form.getPassword();
        String verifyCode = form.getVerifyCode();
        String sessionVerifyCode = (String)request.getSession().getAttribute("session_vcode");
        if(username ==null || username.trim().isEmpty()) {
            errors.put("username","用户名不能为空");
        } else if(username.length()<3 || username.length()>10) {
            errors.put("username","用户名长度必须再3-10之间");
        }
        if(pwd ==null || pwd.trim().isEmpty()) {
            errors.put("password","密码不能为空");
        } else if(pwd.length()<6 || pwd.length()>15) {
            errors.put("password","密码长度必须再6-15之间");
        }
        if(verifyCode ==null || verifyCode.trim().isEmpty()) {
            errors.put("verifyCode","验证码不能为空");
        } else if(verifyCode.length() != 4) {
            errors.put("verifyCode","请输入正确的验证码");
        }  else if(verifyCode.equals(sessionVerifyCode)) {
            errors.put("verifyCode","请输入正确的验证码");
        }


        if(errors!=null&&errors.size()>0) {
            request.setAttribute("errors",errors);
            request.setAttribute("user",form);
            request.getRequestDispatcher("/user/register.jsp").forward(request,response);
            return;
        }

        try {
            userService.register(form);
            response.getWriter().println("<h1>注册成功！</h1>"+
                    "<a href='"+request.getContextPath()+"/user/login.jsp'>点击跳转登录页面</a>");
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            //转发到register.jsp
            request.getRequestDispatcher("/user/register.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
