package com.example.model;

import com.example.dao.DBConnection;
import com.example.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDaoImpl implements BookDao {
    private final Connection conn = DBConnection.createConnection();
    private final String SQL_CREATE_BOOK = "INSERT INTO book (name, author, publisher, category, price, date) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private final String SQL_GET_BOOK_BY_AUTHOR = "SELECT * FROM book WHERE author like ?";
    private final String SQL_GET_BOOK_BY_NAME = "SELECT * FROM book WHERE name like ?";
    private final String SQL_GET_BOOK_BY_PUBLISHER = "SELECT * FROM book WHERE publisher like ?";
    private final String SQL_GET_BOOK_BY_CATEGORY = "SELECT * FROM book WHERE category like ?";
    private final String SQL_BORROW_BOOK = "UPDATE book SET status = ? WHERE id = ?";
    private final String SQL_GET_ALL_BOOK = "SELECT * FROM book";
    private final String SQL_GET_ALL_BOOK_STUDENT_BORROW = "SELECT * FROM book WHERE status = ?";
    private final String SQL_BOOK_IS_BORROW = "SELECT * FROM book WHERE id = ?";
    private final String SQL_RETURN_BOOK = "UPDATE book SET status = 0 WHERE id = ?";

    public BookDaoImpl() throws SQLException {
    }

    @Override
    public void createBook(Book book) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_BOOK,
                Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, book.getName());
            pstm.setString(2, book.getAuthor());
            pstm.setString(3, book.getPublisher());
            pstm.setString(4, book.getCategory());
            pstm.setDouble(5, book.getPrice());
            pstm.setString(6, book.getDate());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Book> getBookByAuthor(String author) {
        ArrayList<Book> allBooks = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_BOOK_BY_AUTHOR)) {
            pstm.setString(1, "%" + author + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt(1));
                    book.setName(rs.getString(2));
                    book.setAuthor(rs.getString(3));
                    book.setPublisher(rs.getString(4));
                    book.setCategory(rs.getString(5));
                    book.setPrice(rs.getDouble(6));
                    book.setDate(rs.getString(7));
                    book.setStatus(rs.getInt(8));
                    allBooks.add(book);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    @Override
    public ArrayList<Book> getBookByName(String name) {
        ArrayList<Book> allBooks = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_BOOK_BY_NAME)) {
            pstm.setString(1, "%" + name + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt(1));
                    book.setName(rs.getString(2));
                    book.setAuthor(rs.getString(3));
                    book.setPublisher(rs.getString(4));
                    book.setCategory(rs.getString(5));
                    book.setPrice(rs.getDouble(6));
                    book.setDate(rs.getString(7));
                    book.setStatus(rs.getInt(8));
                    allBooks.add(book);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    @Override
    public ArrayList<Book> getBookByPublisher(String publisher) {
        ArrayList<Book> allBooks = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_BOOK_BY_PUBLISHER)) {
            pstm.setString(1, "%" + publisher + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt(1));
                    book.setName(rs.getString(2));
                    book.setAuthor(rs.getString(3));
                    book.setPublisher(rs.getString(4));
                    book.setCategory(rs.getString(5));
                    book.setPrice(rs.getDouble(6));
                    book.setDate(rs.getString(7));
                    book.setStatus(rs.getInt(8));
                    allBooks.add(book);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    @Override
    public ArrayList<Book> getBookByCategory(String category) {
        ArrayList<Book> allBooks = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_BOOK_BY_CATEGORY)) {
            pstm.setString(1, "%" + category + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt(1));
                    book.setName(rs.getString(2));
                    book.setAuthor(rs.getString(3));
                    book.setPublisher(rs.getString(4));
                    book.setCategory(rs.getString(5));
                    book.setPrice(rs.getDouble(6));
                    book.setDate(rs.getString(7));
                    book.setStatus(rs.getInt(8));
                    allBooks.add(book);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    @Override
    public void borrowBook(int stId, int id) {
        boolean kt = isBorrow(id);
        if (kt) {
            try (PreparedStatement pstm = conn.prepareStatement(SQL_BORROW_BOOK)) {
                pstm.setInt(1, stId);
                pstm.setInt(2, id);
                int ud = pstm.executeUpdate();
                if (ud > 0) {
                    System.out.println("Update Successful!!!");
                } else {
                    System.out.println("Update Fail!!!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Book was borrowed!!!");
        }
    }

    @Override
    public ArrayList<Book> getAllBook() {
        ArrayList<Book> allBooks = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_ALL_BOOK);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt(1));
                book.setName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPublisher(rs.getString(4));
                book.setCategory(rs.getString(5));
                book.setPrice(rs.getDouble(6));
                book.setDate(rs.getString(7));
                book.setStatus(rs.getInt(8));
                allBooks.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    @Override
    public ArrayList<Book> getAllBookStudentBorrow(int stId) {
        ArrayList<Book> allBooks = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_ALL_BOOK_STUDENT_BORROW)) {
            pstm.setInt(1, stId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt(1));
                    book.setName(rs.getString(2));
                    book.setAuthor(rs.getString(3));
                    book.setPublisher(rs.getString(4));
                    book.setCategory(rs.getString(5));
                    book.setPrice(rs.getDouble(6));
                    book.setDate(rs.getString(7));
                    book.setStatus(rs.getInt(8));
                    allBooks.add(book);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    @Override
    public boolean isBorrow(int id) {
        int st = 0;
        try (PreparedStatement pstm = conn.prepareStatement(SQL_BOOK_IS_BORROW)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    st = rs.getInt(8);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (st == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void returnBook(int id) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_RETURN_BOOK)) {
            pstm.setInt(1, id);
            int ud = pstm.executeUpdate();
            if (ud > 0) {
                System.out.println("Update Successful");
            } else {
                System.out.println("Update Fail");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
