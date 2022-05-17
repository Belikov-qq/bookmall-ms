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

        //获取参数
        String bookId = request.getParameter("bookId");

        //根据bookId修改数据库中记录
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.selectBooksByBookId(bookId);
        int status = 1;

        if(book != null){
            book.setBookName(request.getParameter("name"));
            book.setBookAuthor(request.getParameter("author"));
            book.setBookPrice(request.getParameter("price"));
            book.setBookImgPath(request.getParameter("cover"));
            book.setBookDesc(request.getParameter("desc"));
            book.setBookType(request.getParameter("type"));
        }else {
            status = 0;
        }

        Map<String, Object> map = new HashMap<>();
        //map.put("status", status);
        map.put("status", 1); // 后续改为status
        // String msg = status == 1 ? "修改成功" : "修改失败";
        String msg = "修改成功"; // 后续修改
        map.put("msg", msg);

        // 将map转换为json字符串
        String json = JSON.toJSONString(map);


        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
