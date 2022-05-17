package com.bookmall.ms.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bookmall.ms.dao.BookDAO;
import com.bookmall.ms.service.BookService;

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

        BookService bookService = new BookService();
        boolean flag = bookService.deleteBook(request.getParameter("bookId"));

        // 封装返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("error", flag ? 0 : -1);
        map.put("msg", flag ? "删除成功" : "删除失败");

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
