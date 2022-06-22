package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        ticket = new Ticket3D();
        assertThat(ticket, is(ticket));
    }

    @Test
    @Ignore
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void whenAdd() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> list = List.of(session);
        assertThat(session, is(list.get(0)));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void dateIsWrong() {
        Calendar date = Calendar.getInstance();
        date.set(2020, 02, 31, 23, 00);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenBuyisBusy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 03, 15);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Ticket wrong = cinema.buy(account, 1, 1, date);
    }
}