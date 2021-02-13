package com.sergiomartinrubio.codewars.four;

import com.sergiomartinrubio.codewars.four.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void shouldIncreaseProgressWhenActivityOneBelowTheRank() {
        // GIVEN
        User user = new User();

        // WHEN
        user.incProgress(-7);

        // THEN
        assertEquals(10, user.progress);
    }

    @Test
    public void shouldIncreaseProgressWhenActivityTwoBelowTheRank() {
        // GIVEN
        User user = new User();

        // WHEN
        user.incProgress(-6);

        // THEN
        assertEquals(40, user.progress);
    }

    @Test
    public void shouldIncreaseProgressWhenActivityThreeBelowTheRank() {
        // GIVEN
        User user = new User();

        // WHEN
        user.incProgress(-5);

        // THEN
        assertEquals(90, user.progress);
    }

    @Test
    public void shouldIncreaseProgressWhenActivityFourBelowTheRank() {
        // GIVEN
        User user = new User();

        // WHEN
        user.incProgress(-4);

        // THEN
        assertEquals(60, user.progress);
        assertEquals(-7, user.rank);
    }

    @Test
    public void shouldIncreaseProgressToOneWhenUserRankIsMinusOne() {
        // GIVEN
        User user = new User();
        user.rank = -1;

        // WHEN
        user.incProgress(1);

        // THEN
        assertEquals(10, user.progress);
    }

    @Test
    public void shouldCalculateProgressAndRank() {
        // GIVEN
        User user = new User();

        // WHEN
        // THEN
        assertEquals(-8, user.rank);
        assertEquals(0, user.progress);
        user.incProgress(-7);
        assertEquals(10, user.progress);
        user.incProgress(-5);
        assertEquals(0, user.progress);
        assertEquals(-7, user.rank);
        user.incProgress(-1);
        assertEquals(60, user.progress);
        assertEquals(-4, user.rank);
    }

    @Test
    public void shouldCalculateProgressAndRank2() {
        // GIVEN
        User user = new User();
        user.incProgress(-8);
        user.incProgress(-7);
        user.incProgress(-6);
        user.incProgress(-5);
        user.incProgress(-4);
        user.incProgress(-8);
        user.incProgress(1);
        user.incProgress(1);
        user.incProgress(1);
        user.incProgress(1);
        user.incProgress(1);
        user.incProgress(2);
        user.incProgress(2);
        user.incProgress(-1);
        user.incProgress(3);
        user.incProgress(8);
        user.incProgress(8);
        user.incProgress(8);
        user.incProgress(8);
        user.incProgress(8);
        user.incProgress(8);
        user.incProgress(8);
        user.incProgress(8);
        user.incProgress(8);
        user.incProgress(8);

        // THEN
        assertEquals(8, user.rank);
        assertEquals(0, user.progress);
    }
}