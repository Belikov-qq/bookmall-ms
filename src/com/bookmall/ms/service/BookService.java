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
     * 查询所有图书信息
     * @return
     */
    public List<Book> listBooks() {
        List<Book> bookList = bookDAO.selectBooks();
        return bookList;
    }
}
