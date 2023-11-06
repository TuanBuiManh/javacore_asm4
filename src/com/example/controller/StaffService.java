package com.example.controller;

import com.example.entity.Staff;
import com.example.model.StaffDaoImpl;

import java.sql.SQLException;

public class StaffService {
    StaffDaoImpl staffDao = new StaffDaoImpl();

    public boolean loginPreparedStatementController(Staff staff) throws SQLException {
        return staffDao.checkLoginPreparedStatement(staff);
    }
}
