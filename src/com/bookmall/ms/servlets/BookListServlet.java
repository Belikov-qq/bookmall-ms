package com.bookmall.ms.servlets;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookmall.ms.dto.Book;
import com.bookmall.ms.dao.BookDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "BookListServlet", urlPatterns = "/bookList")
public class BookListServlet extends HttpServlet {
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

        BookDAO bookDAO = new BookDAO();

        // 获取参数
        int page = Integer.parseInt(request.getParameter("page"));

        int limit = 10;  // 每页显示几条
        long total = bookDAO.selectBookCount();  // 总记录数
        long pageCount = total / limit + (total % limit == 0 ? 0 : 1);  // 总页数
        if (page == -1){
            page = (int) pageCount - 1;
        }

        // 依据page获取BookList
        List<Book> bookList = bookDAO.selectBooks(10 * page, 10);

        // 将BookList转换为JSONArray
        JSONArray jsonArray = new JSONArray();
        for (Book book : bookList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", book.getBookId());
            jsonObject.put("name", book.getBookName());
            jsonObject.put("price", book.getBookPrice());
            jsonObject.put("author", book.getBookAuthor());
            jsonObject.put("type", book.getBookType());
            jsonObject.put("desc", book.getBookDesc());
            jsonObject.put("cover", book.getBookImgPath());
            jsonArray.add(jsonObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bookList", jsonArray);
//        jsonObject.put("bookList", null);  // 测试
        jsonObject.put("page", page);
        jsonObject.put("total", pageCount);
//        jsonObject.put("total", 10);  // 测试

        // 转换为json字符串
        String json = JSON.toJSONString(jsonObject);

        // 响应json字符串
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
