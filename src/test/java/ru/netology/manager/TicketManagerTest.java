package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);
    private TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();

    private Ticket one = new Ticket(1, 5500, "DME", "OVB", 270);
    private Ticket two = new Ticket(2, 5300, "DME", "OVB", 275);
    private Ticket three = new Ticket(3, 4500, "DME", "OVB", 270);
    private Ticket four = new Ticket(4, 2100, "LED", "DME", 100);
    private Ticket five = new Ticket(5, 6100, "OVB", "LED", 285);
    private Ticket six = new Ticket(6, 1900, "LED", "DME", 140);
    private Ticket seven = new Ticket(7, 6100, "OVB", "LED", 270);
    private Ticket eight = new Ticket(8, 1850, "LED", "DME", 125);

    // Тесты с сортировкой по времени полета

    @Test
    void shouldSearchTicketsWithSortTime() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("LED", "DME", comparator);
        Ticket[] expected = {four, eight, six};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchTicketsForEqualValuesPriceWithSortTime() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("OVB", "LED", comparator);
        Ticket[] expected = {seven, five};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllTicketsWithSortTime() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);

        Ticket[] actual;
        actual = manager.findByCondition("", "", comparator);
        Ticket[] expected = {four, eight, six, one, three, seven, two, five};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllForDepartureAirportWithSortTime() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("DME", "", comparator);
        Ticket[] expected = {one, three, two};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllForArrivalAirportWithSortTime() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("", "DME", comparator);
        Ticket[] expected = {four, eight, six};

        assertArrayEquals(expected, actual);
    }

    // ТЕСТЫ С СОРТИРОВКОЙ ПО ЦЕНЕ

    @Test
    void shouldSearchTicketsWithSortPrice() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("LED", "DME");
        Ticket[] expected = {eight, six, four};

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
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("OVB", "LED");
        Ticket[] expected = {five, seven};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllTicketsWithSortPrice() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("", "");
        Ticket[] expected = {eight, six, four, three, two, one, five, seven};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllForDepartureAirportWithSortPrice() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("DME", "");
        Ticket[] expected = {three, two, one};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllFoArrivalAirportWithSortPrice() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("", "DME");
        Ticket[] expected = {eight, six, four};

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
        manager.add(eight);

        Ticket[] actual = manager.findByCondition("OVB", "VKO");
        Ticket[] expected = {};

        assertArrayEquals(expected, actual);
    }

}