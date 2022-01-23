package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    private Ticket one = new Ticket(1, 5500, "DME", "OVB", 270);
    private Ticket two = new Ticket(2, 5300, "DME", "OVB", 275);
    private Ticket three = new Ticket(3, 4500, "DME", "OVB", 270);
    private Ticket four = new Ticket(4, 2100, "LED", "DME", 100);
    private Ticket five = new Ticket(5, 6100, "OVB", "LED", 285);
    private Ticket six = new Ticket(6, 1900, "LED", "DME", 140);
    private Ticket seven = new Ticket(7, 6100, "OVB", "LED", 285);

    @Test
    void shouldSearchTickets() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);

        Ticket[] actual = manager.findByCondition("LED", "DME");
        Ticket[] expected = {six, four};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchTicketsForEqualValuesPrice() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);

        Ticket[] actual = manager.findByCondition("OVB", "LED");
        Ticket[] expected = {five, seven};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllTicketsWithSort() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);

        Ticket[] actual = manager.findByCondition("", "");
        Ticket[] expected = {six, four, three, two, one, five, seven};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllForDepartureAirport() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);

        Ticket[] actual = manager.findByCondition("DME", "");
        Ticket[] expected = {three, two, one};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllFoArrivalAirport() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);

        Ticket[] actual = manager.findByCondition("", "DME");
        Ticket[] expected = {six, four};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchTicketsIfNotExist() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);

        Ticket[] actual = manager.findByCondition("OVB", "VKO");
        Ticket[] expected = {};

        assertArrayEquals(expected, actual);
    }

}