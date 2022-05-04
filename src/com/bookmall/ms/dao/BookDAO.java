package com.bookmall.ms.dao;

import com.bookmall.ms.dto.Book;
import com.bookmall.ms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 完成图书信息的数据库操作
 */
public class BookDAO {
    //添加图书信息操作
    private int insertBook(Book book) throws SQLException {
        int i = 0;
        try {
            String sql = "INSERT INTO books(book_id,book_name,book_author,book_price,book_img_path,book_desc,book_stock,book_type) VALUES(?,?,?,?,?,?,?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            //给SQL中的参数赋值
            Object[] params = {book.getBookId(), book.getBookName(), book.getBookAuthor(), book.getBookPrice(), book.getBookImgPath(), book.getBookDesc(), book.getBookType()};
            i = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public List<Book> selectBooks(){
        List<Book> bookList = null;
        try {
            String sql = "";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            bookList = (List<Book>) queryRunner.query(sql, new BeanHandler<Book>(Book.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
