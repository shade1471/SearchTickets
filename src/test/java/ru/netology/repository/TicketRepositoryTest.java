package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Ticket;
import ru.netology.manager.TicketManager;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    private Ticket one = new Ticket(1, 45000, "DME", "OVB", 53000);
    private Ticket two = new Ticket(2, 30000, "DME", "OVB", 53000);
    private Ticket three = new Ticket(3, 47000, "DME", "OVB", 53000);
    private Ticket four = new Ticket(4, 4500, "LED", "DME", 53000);
    private Ticket five = new Ticket(5, 4500, "OVB", "LED", 53000);
    private Ticket six = new Ticket(6, 4300, "LED", "DME", 53000);
    private Ticket seven = new Ticket(7, 4500, "OVB", "LED", 53000);

    @Test
    void shouldSearchIfRepoIsEmpty() {
        repository.removeAll();

        Ticket[] actual = manager.findByCondition("OVB", "LED");
        Ticket[] expected = {};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchById() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);

        Ticket[] actual = repository.findById(6);
        Ticket[] expected = {six};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByIdIfNotExist() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);

        Ticket[] actual = repository.findById(21);

        assertArrayEquals(null, actual);
    }

    @Test
    void shouldRemoveById() {
        repository.save(one);
        repository.save(two);
        repository.save(three);
        repository.save(four);
        repository.removeById(3);

        Ticket[] expected = {one, two, four};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdIfNotExistAndCatchException() {
        repository.save(one);
        repository.save(two);
        repository.save(three);
        repository.save(four);


        assertThrows(NotFoundException.class, () -> {
            repository.removeById(5);
        });
    }

}