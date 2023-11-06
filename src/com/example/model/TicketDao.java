package com.example.model;

import com.example.entity.Book;
import com.example.entity.Ticket;

import java.util.ArrayList;

public interface TicketDao {
    public void createTicket(Ticket ticket);

    public Ticket getTicketById(int ticketId);

    public void updateTicket(Ticket ticket);

    public void updateDateReturn(String dateReturn, int ticketId);
}
