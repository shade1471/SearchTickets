package ru.netology.repository;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.Ticket;

public class TicketRepository {
    private Ticket[] tickets = new Ticket[0];

    public void save(Ticket ticket) {
        int length = tickets.length + 1;
        Ticket[] tmp = new Ticket[length];

        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = ticket;
        tickets = tmp;
    }

    public Ticket[] findAll() {
        return tickets;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id=" + id + " not found");
        }
        int length = tickets.length - 1;
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                tmp[index] = ticket;
                index++;
            }
        }
        tickets = tmp;
    }

    public Ticket[] findById(int id) {
        Ticket[] tmp = new Ticket[1];
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                tmp[0] = ticket;
                return tmp;
            }
        }
        return null;
    }

    public Ticket[] removeAll() {
        this.tickets = new Ticket[0];
        return tickets;
    }

}
