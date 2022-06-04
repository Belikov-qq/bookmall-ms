package com.bookmall.ms.dto;

/**
 * 图书信息实体类
 */
public class Book {
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookPrice;
    private String bookStock;
    private String bookDesc;
    private String bookType;

    public Book() {
    }

    public Book(String bookId, String bookName, String bookAuthor, String bookPrice, String bookStock, String bookDesc, String bookType) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.bookStock = bookStock;
        this.bookDesc = bookDesc;
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPrice='" + bookPrice + '\'' +
                ", bookStock='" + bookStock + '\'' +
                ", bookDesc='" + bookDesc + '\'' +
                ", bookType='" + bookType + '\'' +
                '}';
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookStock() {
        return bookStock;
    }

    public void setBookStock(String bookStock) {
        this.bookStock = bookStock;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
}
