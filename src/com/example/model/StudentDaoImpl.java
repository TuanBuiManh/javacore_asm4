package com.example.model;

import com.example.dao.DBConnection;
import com.example.entity.Book;
import com.example.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDaoImpl implements StudentDao{
    private final Connection conn = DBConnection.createConnection();
    private final String SQL_CREATE_STUDENT = "INSERT INTO student (stName, address, phone) " +
            "VALUES (?, ?, ?)";
    private final String SQL_GET_ALL_BOOK = "SELECT * FROM student";

    public StudentDaoImpl() throws SQLException {
    }

    @Override
    public void createStudent(Student student) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_STUDENT,
                Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, student.getStName());
            pstm.setString(2, student.getAddress());
            pstm.setString(3, student.getPhone());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setStId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> allStudents = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_ALL_BOOK);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setStId(rs.getInt(1));
                student.setStName(rs.getString(2));
                student.setAddress(rs.getString(3));
                student.setPhone(rs.getString(4));
                allStudents.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allStudents;
    }
}
