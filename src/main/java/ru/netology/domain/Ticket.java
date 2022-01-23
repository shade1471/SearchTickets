package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Ticket implements Comparable<Ticket> {
    private int id;
    private int price;
    String from;
    String to;
    int travelTime;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", travelTime=" + travelTime +
                '}';
    }

    @Override
    public int compareTo(Ticket o) {
        return this.price - o.price;
    }

    public boolean matches(String from, String to) {
        if (getFrom().contains(from) && getTo().contains(to)) {
            return true;
        }
        return false;
    }

}

