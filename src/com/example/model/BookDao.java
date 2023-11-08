package com.example.model;

import com.example.entity.Book;

import java.util.ArrayList;

public interface BookDao {

    public void createBook(Book book);

    public ArrayList<Book> getBookByAuthor(String author);

    public ArrayList<Book> getBookByName(String name);

    public ArrayList<Book> getBookByPublisher(String publisher);

    public ArrayList<Book> getBookByCategory(String category);

    public void borrowBook(int stId, int id);

    public ArrayList<Book> getAllBook();

    public ArrayList<Book> getAllBookStudentBorrow(int stId);

    public boolean isBorrow(int id);

    public void returnBook(int id);

    public ArrayList<Book> getAllBookIsBorrow();
}
