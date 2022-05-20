package com.bookmall.ms.servlets;

import com.alibaba.fastjson.JSON;
import com.bookmall.ms.dao.BookDAO;
import com.bookmall.ms.dao.UserDAO;
import com.bookmall.ms.dto.User;

import javax.servlet.ServletException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.selectUserByUserName(request.getParameter("UserName"));
        int status = 0;
        if(user != null){
            if(user.getUserPwd() == request.getParameter("UserPws")){
                status = 1;
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
         String msg = status == 1 ? "登录成功" : "登录失败";
        map.put("msg", msg);

        String json = JSON.toJSONString(map);

        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
