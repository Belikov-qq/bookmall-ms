package com.bookmall.ms.servlets;

import com.alibaba.fastjson.JSON;
import com.bookmall.ms.dto.User;
import com.bookmall.ms.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        UserService userService = new UserService();
        User user = userService.checkLogin(request.getParameter("username"), request.getParameter("password"), request.getParameter("salt"));
        int status = 0;
        if (user != null) {
            status = 1;
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        String msg = status == 1 ? "登录成功" : "用户名或密码错误";
        map.put("msg", msg);

        String json = JSON.toJSONString(map);

        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
