package com.bookmall.ms.service;

import com.bookmall.ms.dao.BookDAO;
import com.bookmall.ms.dto.Book;

import java.util.List;

//图书管理的业务实现
public class BookService {

    private BookDAO bookDAO = new BookDAO();

    /**
     * 添加图书业务实现
     * @param book
     * @return
     */
    public boolean saveBook(Book book) {
        int i = bookDAO.insertBook(book);
        return i > 0 ? true : false;
    }

    /**
     * 删除图书业务实现
     * @param bookId
     * @return
     */
    public boolean deleteBook(String bookId) {
        int i = bookDAO.deleteBook(bookId);
        return i > 0 ? true : false;
    }

    /**
     * 根据ID查询一个图书的业务实现
     * @param bookId
     * @return
     */
    public Book getBook(String bookId) {
        Book book = bookDAO.selectBooksByBookId(bookId);
        return book;
    }

    /**
     * 修改图书业务实现
     * @param book
     * @return
     */
    public boolean modifyBook(Book book) {
        int i = bookDAO.updateBook(book);
        return i > 0 ? true : false;
    }

    /**
     * 查询分页图书信息
     * @param pageNum 查询页码
     * @param pageSize 页面容量
     * @return 分页图书信息
     */
    public List<Book> listBooksByPage(int pageNum,int pageSize) {
        int start=(pageNum-1)*pageSize;
        List<Book> bookList = bookDAO.selectBooks(start,pageSize);
        return bookList;
    }

    /**
     * 查询总页数
     * @param pageSize 页面容量
     * @return 页面总数
     */

    public int getPageCount(int pageSize){
        long count=bookDAO.selectBookCount();
        long pageCount=count%pageSize==0?count/pageSize:count/pageSize+1;
        return (int)pageCount;
    }
}
