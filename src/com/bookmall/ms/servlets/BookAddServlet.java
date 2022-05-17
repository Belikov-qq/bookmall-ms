package com.bookmall.ms.servlets;

import com.alibaba.fastjson.JSON;
import com.bookmall.ms.dao.BookDAO;
import com.bookmall.ms.dto.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BookAddServlet")
public class BookAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // 请使用POST方式访问
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        Book book = new Book();
        //设置参数
        book.setBookId(request.getParameter("bookId"));
        book.setBookName(request.getParameter("name"));
        book.setBookAuthor(request.getParameter("author"));
        book.setBookPrice(request.getParameter("price"));
        book.setBookImgPath(request.getParameter("cover"));
        book.setBookDesc(request.getParameter("desc"));
        book.setBookType(request.getParameter("type"));

        //向数据库中添加记录
        BookDAO bookDAO = new BookDAO();
        int status = bookDAO.insertBook(book);

        // 封装返回数据
        Map<String, Object> map = new HashMap<>();
        // map.put("status", status);
        map.put("status", 1); // 后续改为status
        // String msg = status == 1 ? "添加成功" : "添加失败";
        String msg = "添加成功"; // 后续修改
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
