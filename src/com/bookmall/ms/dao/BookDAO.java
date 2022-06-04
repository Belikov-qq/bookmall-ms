package com.bookmall.ms.dao;

import com.bookmall.ms.dto.Book;
import com.bookmall.ms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 完成图书信息的数据库操作
 */

public class BookDAO {

    /**
     * 添加图书信息操作
     */
    public int insertBook(Book book){
        int i = 0;
        try {
            String sql = "INSERT INTO books(book_id,book_name,book_author,book_price,book_desc,book_stock,book_type) VALUES(?,?,?,?,?,?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            //给SQL中的参数赋值
            Object[] params = {book.getBookId(), book.getBookName(), book.getBookAuthor(), book.getBookPrice(), book.getBookDesc(),book.getBookStock(), book.getBookType()};
            i = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 查询所有图书信息
     * @param start 查询数据起始
     * @param limit 查询数据量
     * @return 返回一页图书信息
     */
    public List<Book> selectBooks(int start,int limit){
        List<Book> bookList = new ArrayList<Book>(limit);
        try {
            String sql = "SELECT book_id bookId, book_name bookName, book_author bookAuthor, book_price bookPrice, book_desc bookDesc, book_stock bookStock, book_type bookType FROM books limit ?,? ";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            bookList = (List<Book>) queryRunner.query(sql, new BeanListHandler<Book>(Book.class),start,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    /**
     * 查询总记录数
     * @return 返回总记录数
     */
    public long selectBookCount(){
        long count=0;
        try{
        String sql="select count(1) from books";
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        count=queryRunner.query(sql,new ScalarHandler<Long>());
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return count;
    }


    /**
     * 根据图书ID删除一个图书信息
     */
    public int deleteBook(String bookId){
        int i = 0;
        try {
            String sql = "delete from books where book_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, bookId);
            System.out.println(i);
            System.out.println(bookId);
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
            String sql = "SELECT book_id bookId, book_name bookName, book_author bookAuthor, book_price bookPrice, book_desc bookDesc, book_stock bookStock, book_type bookType FROM books where book_id=?";
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
    public int updateBook(Book book){
        int i = 0;
        try {
            String sql = "update books set book_name=?,book_author=?,book_price=?,book_desc=?,book_stock=?,book_type=? where book_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            //给SQL中的参数赋值
            Object[] params = {book.getBookName(), book.getBookAuthor(), book.getBookPrice(), book.getBookDesc(),book.getBookStock(), book.getBookType(), book.getBookId()};
            i = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
