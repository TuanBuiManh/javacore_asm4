package com.example.model;

import com.example.entity.Book;
import com.example.entity.Staff;

import java.sql.SQLException;

public interface StaffDao {

    public boolean checkLoginPreparedStatement(Staff staff) throws SQLException;

}
