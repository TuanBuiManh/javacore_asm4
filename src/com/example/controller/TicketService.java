package com.example.controller;

import com.example.entity.Ticket;
import com.example.model.TicketDaoImpl;

import java.sql.SQLException;

public class TicketService {
    TicketDaoImpl ticketDao = new TicketDaoImpl();

    public TicketService() throws SQLException {
    }

    public void createTicket(Ticket ticket){
        ticketDao.createTicket(ticket);
    };

    public Ticket getTicketById(int ticketId){
        return ticketDao.getTicketById(ticketId);
    };

    public void updateTicket(Ticket ticket){
        ticketDao.updateTicket(ticket);
    };

    public void updateDateReturn(String dateReturn, int ticketId){
        ticketDao.updateDateReturn(dateReturn, ticketId);
    };
}
