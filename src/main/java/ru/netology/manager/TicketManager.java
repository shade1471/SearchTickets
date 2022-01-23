package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    // Метод поиска с сортировкой по цене

    public Ticket[] findByCondition(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (ticket.matches(from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    // Метод поиска с сортировкой по времени полета

    public Ticket[] findByCondition(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0];
        comparator = new TicketByTravelTimeAscComparator();
        for (Ticket ticket : repository.findAll()) {
            if (ticket.matches(from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }
}

