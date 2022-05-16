package com.bookmall.ms.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bookmall.ms.dao.BookDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BookDeleteServlet", urlPatterns = "/delete")
public class BookDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        // 请使用POST方式访问
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        // 获取参数
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        // 依据bookId删除数据库中的记录
        BookDAO bookDAO = new BookDAO();
        int status = bookDAO.deleteBook(String.valueOf(bookId));

        // 封装返回数据
        Map<String, Object> map = new HashMap<>();
        // map.put("status", status);
        map.put("status", 1); // 后续改为status
        // String msg = status == 1 ? "删除成功" : "删除失败";
        String msg = "删除成功"; // 后续修改
        map.put("msg", msg);

        // 将map转换为json字符串
        String json = JSON.toJSONString(map);

        // 响应json字符串
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
