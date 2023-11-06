package com.example.entity;

public class Ticket {
    private int ticketId;
    private int studentId;
    private int bookId;
    private String dateBorrow;
    private String dateReturn;
    private String createAt;

    public Ticket() {
    }

    public Ticket(int studentId, int bookId, String dateBorrow, String dateReturn, String createAt) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
        this.createAt = createAt;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(String dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
