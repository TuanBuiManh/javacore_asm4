package com.example.ui;

import com.example.controller.BookService;
import com.example.controller.StaffService;
import com.example.controller.StudentService;
import com.example.controller.TicketService;
import com.example.entity.Book;
import com.example.entity.Staff;
import com.example.entity.Student;
import com.example.entity.Ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    BookService bookService = new BookService();
    StudentService studentService = new StudentService();
    StaffService staffService = new StaffService();
    TicketService ticketService = new TicketService();
    private final Scanner sc;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Staff staff = new Staff();

    public Console() throws IOException, SQLException {
        this.sc = new Scanner(System.in);
    }

    private int menu() {
        System.out.println("---LIBRARY MANAGEMENT---");
        System.out.println("1. Book");
        System.out.println("2. Student");
        System.out.println("3. Staff");
        System.out.println("0. Exit");
        int choice = readInt(0, 3);
        return choice;
    }

    private int menuBook() {
        System.out.println("---BOOK MANAGEMENT---");
        System.out.println("1. Add Book");
        System.out.println("2. Search Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. Get All Book");
        System.out.println("5. Get All Book Student Borrow");
        System.out.println("6. Return Book");
        System.out.println("7. Back");
        int choice = readInt(1, 7);
        return choice;
    }

    private int menuSearchBook() {
        System.out.println("---SEARCH FOR BOOK---");
        System.out.println("1. Search By Name");
        System.out.println("2. Search By Author");
        System.out.println("3. Search By Publisher");
        System.out.println("4. Search By Category");
        System.out.println("5. Back");
        int choice = readInt(1, 5);
        return choice;
    }

    private int menuStudent() {
        System.out.println("---STUDENT MANAGEMENT---");
        System.out.println("1. Add Student");
        System.out.println("2. Get All Student");
        System.out.println("3. Back");
        int choice = readInt(1, 3);
        return choice;
    }

    private int menuStaff() {
        System.out.println("---Staff MANAGEMENT---");
        System.out.println("1. Add Ticket");
        System.out.println("2. Get Ticket By Id");
        System.out.println("3. Update Ticket");
        System.out.println("4. Update Date Return");
        System.out.println("5. Back");
        int choice = readInt(1, 5);
        return choice;
    }

    public void start() throws SQLException, ParseException {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    int choice1 = menuBook();
                    switch (choice1) {
                        case 1:
                            addBook();
                            break;
                        case 2:
                            int choice2 = menuSearchBook();
                            switch (choice2) {
                                case 1:
                                    getBookByName();
                                    break;
                                case 2:
                                    getBookByAuthor();
                                    break;
                                case 3:
                                    getBookByPublisher();
                                    break;
                                case 4:
                                    getBookByCategory();
                                    break;
                                case 5:
                                    break;
                            }
                            break;
                        case 3:
                            borrowBook();
                            break;
                        case 4:
                            showAllBooks();
                            break;
                        case 5:
                            getAllBookStudentBorrow();
                            break;
                        case 6:
                            returnBook();
                            break;
                        case 7:
                            break;
                    }
                    break;
                case 2:
                    int choice3 = menuStudent();
                    switch (choice3) {
                        case 1:
                            addStudent();
                            break;
                        case 2:
                            showAllStudents();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println("Staff Login !!!");
                        boolean result = loginPreparedStatement();
                        if (result) {
                            break;
                        } else {
                            System.out.println("Login fail, Please login again !!!");
                        }
                    }
                    int choice4 = menuStaff();
                    switch (choice4) {
                        case 1:
                            addTicket();
                            break;
                        case 2:
                            getTicketById();
                            break;
                        case 3:
                            updateTicket();
                            break;
                        case 4:
                            updateDateBorrow();
                            break;
                        case 5:
                            break;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }


    private int readInt(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
        }
        return choice;
    }

    private double readDouble(int min, double max) {
        double price;
        while (true) {
            try {
                price = Integer.parseInt(sc.nextLine());
                if (price >= min && price <= max) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
        }
        return price;
    }

    private void addBook() throws ParseException {
        System.out.println("Enter book name: ");
        String name = sc.nextLine();
        System.out.println("Enter book author: ");
        String author = sc.nextLine();
        System.out.println("Enter book publisher: ");
        String publisher = sc.nextLine();
        System.out.println("Enter book category: ");
        String category = sc.nextLine();
        System.out.println("Enter book price: ");
        double price = readDouble(0, Double.MAX_VALUE);
        System.out.println("Enter book date: ");
        String date = sc.nextLine();
        Book book = new Book(name, author, publisher, category, price, date);
        bookService.createBook(book);
    }

    private void getBookByName() {
        System.out.println("Enter name of book: ");
        String name = sc.nextLine();
        System.out.println("ID\tName\tAuthor\tPublisher\tCategory\tPrice\tDate\tStatus");
        ArrayList<Book> allBooks = bookService.getBookByName(name);
        allBooks.forEach(book -> {
            System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublisher()
                    + "\t" + book.getCategory() + "\t" + book.getPrice() + "\t" + book.getDate() + "\t" + book.getStatus());
        });
    }

    private void getBookByAuthor() {
        System.out.println("Enter author of book: ");
        String author = sc.nextLine();
        System.out.println("ID\tName\tAuthor\tPublisher\tCategory\tPrice\tDate\tStatus");
        ArrayList<Book> allBooks = bookService.getBookByAuthor(author);
        allBooks.forEach(book -> {
            System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublisher()
                    + "\t" + book.getCategory() + "\t" + book.getPrice() + "\t" + book.getDate() + "\t" + book.getStatus());
        });
    }

    private void getBookByPublisher() {
        System.out.println("Enter publisher of book: ");
        String publisher = sc.nextLine();
        System.out.println("ID\tName\tAuthor\tPublisher\tCategory\tPrice\tDate\tStatus");
        ArrayList<Book> allBooks = bookService.getBookByPublisher(publisher);
        allBooks.forEach(book -> {
            System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublisher()
                    + "\t" + book.getCategory() + "\t" + book.getPrice() + "\t" + book.getDate() + "\t" + book.getStatus());
        });
    }

    private void getBookByCategory() {
        System.out.println("Enter category of book: ");
        String category = sc.nextLine();
        System.out.println("ID\tName\tAuthor\tPublisher\tCategory\tPrice\tDate\tStatus");
        ArrayList<Book> allBooks = bookService.getBookByCategory(category);
        allBooks.forEach(book -> {
            System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublisher()
                    + "\t" + book.getCategory() + "\t" + book.getPrice() + "\t" + book.getDate() + "\t" + book.getStatus());
        });
    }

    private void borrowBook() {
        System.out.println("Enter student id: ");
        int stId = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter book id: ");
        int id = readInt(0, Integer.MAX_VALUE);
        bookService.borrowBook(stId, id);
    }

    private void showAllBooks() {
        System.out.println("--All Book--");
        System.out.println("ID\tName\tAuthor\tPublisher\tCategory\tPrice\tDate\tStatus");
        ArrayList<Book> allBooks = bookService.getAllBook();
        allBooks.forEach(book -> {
            System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublisher()
                    + "\t" + book.getCategory() + "\t" + book.getPrice() + "\t" + book.getDate() + "\t" + book.getStatus());
        });
    }

    private void getAllBookStudentBorrow() {
        System.out.println("Enter student id: ");
        int stId = readInt(0, Integer.MAX_VALUE);
        System.out.println("--All Book Student Borrow--");
        System.out.println("ID\tName\tAuthor\tPublisher\tCategory\tPrice\tDate\tStatus");
        ArrayList<Book> allBooks = bookService.getAllBookStudentBorrow(stId);
        allBooks.forEach(book -> {
            System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublisher()
                    + "\t" + book.getCategory() + "\t" + book.getPrice() + "\t" + book.getDate() + "\t" + book.getStatus());
        });
    }

    private void addStudent() {
        System.out.println("Enter student name: ");
        String stName = sc.nextLine();
        System.out.println("Enter student address: ");
        String address = sc.nextLine();
        System.out.println("Enter student phone: ");
        String phone = sc.nextLine();
        Student student = new Student(stName, address, phone);
        studentService.createStudent(student);
    }

    private void showAllStudents() {
        System.out.println("--All Student--");
        System.out.println("ID\tName\tAddress\tPhone");
        ArrayList<Student> allStudents = studentService.getAllStudent();
        allStudents.forEach(student -> {
            System.out.println(student.getStId() + "\t" + student.getStName() + "\t" + student.getAddress()
                    + "\t" + student.getPhone());
        });
    }

    private boolean loginPreparedStatement() throws SQLException {
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        staff.setUserName(username);
        staff.setPassWord(password);
        return staffService.loginPreparedStatementController(staff);
    }

    private void addTicket() {
        System.out.println("Enter Student Id: ");
        int studentId = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter Book Id: ");
        int bookId = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter Date Borrow: ");
        String dateBorrow = sc.nextLine();
        System.out.println("Enter Date Return: ");
        String dateReturn = sc.nextLine();
        System.out.println("Enter Create At ");
        String createAt = sc.nextLine();
        Ticket ticket = new Ticket(studentId, bookId, dateBorrow, dateReturn, createAt);
        ticketService.createTicket(ticket);
    }

    private void getTicketById() {
        System.out.println("Enter ID of ticket");
        int ticketId = readInt(0, Integer.MAX_VALUE);
        Ticket ticket = ticketService.getTicketById(ticketId);
        System.out.println("TicketId\tStudentId\tBookId\tDateBorrow\tDateReturn\tCreateAt");
        System.out.println(ticket.getTicketId() + "\t\t\t" + ticket.getStudentId() + "\t\t\t"
                + ticket.getBookId() + "\t\t" + ticket.getDateBorrow() + "\t"
                + ticket.getDateReturn() + "\t" + ticket.getCreateAt());
    }

    private void updateTicket() {
        System.out.println("Enter student id: ");
        int studentId = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter book id: ");
        int bookId = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter date borrow");
        String dateBorrow = sc.nextLine();
        System.out.println("Enter date return");
        String dateReturn = sc.nextLine();
        System.out.println("Enter create at");
        String createAt = sc.nextLine();
        Ticket ticket = new Ticket(studentId, bookId, dateBorrow, dateReturn, createAt);
        ticketService.updateTicket(ticket);
    }

    private void updateDateBorrow() {
        System.out.println("Enter ticket id");
        int ticketId = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter date borrow");
        String dateBorrow = sc.nextLine();
        ticketService.updateDateReturn(dateBorrow, ticketId);
    }

    private void returnBook() {
        System.out.println("Enter book id");
        int id = readInt(0, Integer.MAX_VALUE);
        bookService.returnBook(id);
    }
}
