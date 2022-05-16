package com.bookmall.ms.servlets;

import com.alibaba.fastjson.JSONObject;
import com.bookmall.ms.dao.BookDAO;
import com.bookmall.ms.dto.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookquery")  // 暂定
public class BookQueryServlet extends HttpServlet {
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

        int id = Integer.parseInt(request.getParameter("id"));

        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.selectBooksByBookId(String.valueOf(id));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", book.getBookId());
        jsonObject.put("name", book.getBookName());
        jsonObject.put("price", book.getBookPrice());
        jsonObject.put("author", book.getBookAuthor());
        jsonObject.put("type", book.getBookType());
        jsonObject.put("desc", book.getBookDesc());
        jsonObject.put("cover", book.getBookImgPath());
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result", jsonObject);

        // 转换为JSON字符串
        String jsonString = jsonObject1.toJSONString();

        try {
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}