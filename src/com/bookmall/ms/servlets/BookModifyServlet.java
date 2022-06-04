package com.bookmall.ms.servlets;

import com.alibaba.fastjson.JSON;
import com.bookmall.ms.dao.BookDAO;
import com.bookmall.ms.dto.Book;
import com.bookmall.ms.service.BookService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.bookmall.ms.service.HtmlEscaper.escape;

@WebServlet(name = "BookModifyServlet")
public class BookModifyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        // 请使用POST方式访问
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        //根据bookId修改数据库中记录
        BookService bookService = new BookService();
        Book book = new Book();
        book.setBookId(escape(request.getParameter("bookId")));
        book.setBookName(escape(request.getParameter("name")));
        book.setBookAuthor(escape(request.getParameter("author")));
        book.setBookPrice(escape(request.getParameter("price")));
        book.setBookStock(escape(request.getParameter("stock")));
        book.setBookDesc(escape(request.getParameter("desc")));
        book.setBookType(escape(request.getParameter("type")));

        boolean flag = bookService.modifyBook(book);


        Map<String, Object> map = new HashMap<>();
        map.put("error", flag ? 0 : -1);
        map.put("msg", flag ? "修改成功" : "修改失败");

        // 将map转换为json字符串
        String json = JSON.toJSONString(map);


        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
