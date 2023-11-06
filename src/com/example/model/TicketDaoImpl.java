package com.example.model;

import com.example.dao.DBConnection;
import com.example.entity.Ticket;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDaoImpl implements TicketDao{
    private final Connection conn = DBConnection.createConnection();
    private final String SQL_CREATE_TICKET = "INSERT INTO ticket (studentId, bookId, dateBorrow, dateReturn, createAt) " +
            "VALUES (?, ?, ?, ?, ?)";
    private final String SQL_GET_TICKET_BY_ID = "SELECT * FROM ticket WHERE ticketId like ?";
    private final String SQL_UPDATE_TICKET = "UPDATE ticket SET studentId = ?, bookId = ?, dateBorrow = ? , dateReturn = ?, " +
            "createAt = ? WHERE ticketId = ?";
    private final String SQL_UPDATE_DATE_RETURN = "UPDATE ticket SET dateReturn = ? WHERE ticketId = ?";

    public TicketDaoImpl() throws SQLException {
    }

    @Override
    public void createTicket(Ticket ticket) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_TICKET,
                Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, ticket.getStudentId());
            pstm.setInt(2, ticket.getBookId());
            pstm.setString(3, ticket.getDateBorrow());
            pstm.setString(4, ticket.getDateReturn());
            pstm.setString(5, ticket.getCreateAt());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ticket.setTicketId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        Ticket ticket = new Ticket();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_TICKET_BY_ID)) {
            pstm.setInt(1, ticketId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    ticket.setTicketId(rs.getInt(1));
                    ticket.setStudentId(rs.getInt(2));
                    ticket.setBookId(rs.getInt(3));
                    ticket.setDateBorrow(rs.getString(4));
                    ticket.setDateReturn(rs.getString(5));
                    ticket.setCreateAt(rs.getString(6));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ticket;
    }

    @Override
    public void updateTicket(Ticket ticket) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE_TICKET)) {
            pstm.setInt(1, ticket.getStudentId());
            pstm.setInt(2, ticket.getBookId());
            pstm.setString(3, ticket.getDateBorrow());
            pstm.setString(4, ticket.getDateReturn());
            pstm.setString(5, ticket.getCreateAt());
            pstm.setInt(6, ticket.getTicketId());
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

    @Override
    public void updateDateReturn(String dateReturn, int ticketId) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE_DATE_RETURN)) {
            pstm.setString(1, dateReturn);
            pstm.setInt(2, ticketId);
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
