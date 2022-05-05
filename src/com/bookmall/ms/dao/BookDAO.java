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

    /**
     * 添加图书信息操作
     */
    private int insertBook(Book book){
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

    /**
     * 查询所有图书信息
     */
    public List<Book> selectBooks(){
        List<Book> bookList = null;
        try {
            String sql = "SELECT book_id bookId, book_name bookName, book_author bookAuthor, book_price bookPrice, book_img_path bookImaPath, book_desc bookDesc, book_stock bookStock, book_type bookType FROM books";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            bookList = (List<Book>) queryRunner.query(sql, new BeanHandler<Book>(Book.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    /**
     * 根据图书ID删除一个图书信息
     */
    private int deleteBook(String bookId){
        int i = 0;
        try {
            String sql = "delete from books where book_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据图书ID查询一个图书信息
     */
    public Book selectBooksByBookId(String bookId){
        Book book = null;
        try {
            String sql = "SELECT book_id bookId, book_name bookName, book_author bookAuthor, book_price bookPrice, book_img_path bookImaPath, book_desc bookDesc, book_stock bookStock, book_type bookType FROM books where book_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            book = queryRunner.query(sql, new BeanHandler<Book>(Book.class), bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    /**
     * 根据图书ID修改一个图书信息
     */
    private int updateBook(Book book){
        int i = 0;
        try {
            String sql = "update books set book_name=?,book_author=?,book_price=?,book_img_path=?,book_desc=?,book_stock=?,book_type=? where book_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            //给SQL中的参数赋值
            Object[] params = {book.getBookName(), book.getBookAuthor(), book.getBookPrice(), book.getBookImgPath(), book.getBookDesc(), book.getBookType(), book.getBookId()};
            i = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
