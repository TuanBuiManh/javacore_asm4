package com.example.model;

import com.example.dao.DBConnection;
import com.example.entity.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffDaoImpl implements StaffDao{
    private static PreparedStatement pstm = null;

    @Override
    public boolean checkLoginPreparedStatement(Staff staff) throws SQLException {
        Connection conn = DBConnection.createConnection();
        String dbQuery = "SELECT userName FROM staff WHERE userName like ? and passWord like ?";
        try {
            pstm = conn.prepareStatement(dbQuery);
            pstm.setString(1, staff.getUserName());
            pstm.setString(2, staff.getPassWord());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                System.out.println("Welcome: " + rs.getString("userName"));
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
