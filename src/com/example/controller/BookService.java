package com.example.controller;

import com.example.entity.Book;
import com.example.model.BookDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookService {
    BookDaoImpl bookDao = new BookDaoImpl();

    public BookService() throws SQLException {
    }

    public void createBook(Book book) {
        bookDao.createBook(book);
    }

    public ArrayList<Book> getBookByAuthor(String author) {
        return bookDao.getBookByAuthor(author);
    }

    public ArrayList<Book> getBookByName(String name) {
        return bookDao.getBookByName(name);
    }

    public ArrayList<Book> getBookByPublisher(String publisher) {
        return bookDao.getBookByPublisher(publisher);
    }

    public ArrayList<Book> getBookByCategory(String category) {
        return bookDao.getBookByCategory(category);
    }

    public void borrowBook(int stId, int id) {
        bookDao.borrowBook(stId, id);
    }

    public ArrayList<Book> getAllBook() {
        return bookDao.getAllBook();
    }

    public ArrayList<Book> getAllBookStudentBorrow(int stId) {
        return bookDao.getAllBookStudentBorrow(stId);
    }

    public void returnBook(int id){
        bookDao.returnBook(id);
    }
}
