package model;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class GameTest {
Game game = new Game();

    @org.junit.Test
    public void getGamer1() {
        game.Down1();
        int actual1 = game.getGamer1();
        int expected1 = 348;
        assertEquals(expected1, actual1);
        game.Down1();
        game.Down1();
        game.Down1();
        game.Down1();
        actual1 = game.getGamer1();
        expected1 = 520;
        assertEquals(expected1, actual1);
    }

    @org.junit.Test
    public void getGamer2() {
        game.Down2();
        int actual1 = game.getGamer2();
        int expected1 = 348;
        assertEquals(expected1, actual1);
        game.Down2();
        game.Down2();
        game.Down2();
        game.Down2();
        actual1 = game.getGamer2();
        expected1 = 520;
        assertEquals(expected1, actual1);
    }

    @org.junit.Test
    public void getBallX() {
            int actual1 = game.getBallX();
            int expected1 = 400;
            assertEquals(expected1, actual1);
    }

    @org.junit.Test
    public void getBallY() {
        int actual1 = game.getBallY();
        int expected1 = 300;
        assertEquals(expected1, actual1);
    }

    @org.junit.Test
    public void down1() {
        game.Down1();
        int actual1 = game.getGamer1();
        int expected1 = 348;
        assertEquals(expected1, actual1);
        game.Down1();
        game.Down1();
        game.Down1();
        game.Down1();
        actual1 = game.getGamer1();
        expected1 = 520;
        assertEquals(expected1, actual1);
    }

    @org.junit.Test
    public void down2() {
        game.Down2();
        int actual1 = game.getGamer2();
        int expected1 = 348;
        assertEquals(expected1, actual1);
        game.Down2();
        game.Down2();
        game.Down2();
        game.Down2();
        actual1 = game.getGamer2();
        expected1 = 520;
        assertEquals(expected1, actual1);
    }

    @org.junit.Test
    public void up1() {
        game.Up1();
        int actual1 = game.getGamer1();
        int expected1 = 252;
        assertEquals(expected1, actual1);
        game.Up1();
        game.Up1();
        game.Up1();
        game.Up1();
        actual1 = game.getGamer1();
        expected1 = 55;
        assertEquals(expected1, actual1);
    }

    @org.junit.Test
    public void up2() {
        game.Up2();
        int actual1 = game.getGamer2();
        int expected1 = 252;
        assertEquals(expected1, actual1);
        game.Up2();
        game.Up2();
        game.Up2();
        game.Up2();
        actual1 = game.getGamer2();
        expected1 = 55;
        assertEquals(expected1, actual1);
    }

    @org.junit.Test
    public void getCount1() {
        try {
            int actual1 = game.getCount1();
            int expected1 = 0;
            assertEquals(expected1, actual1);
            game.setTime();
            TimeUnit.SECONDS.sleep(10);
            actual1 = game.getCount1();
            expected1 = 1;
            assertEquals(expected1, actual1);
            game.setTime();
            TimeUnit.SECONDS.sleep(10);
            actual1 = game.getCount1();
            expected1 = 1;
            assertEquals(expected1, actual1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void getCount2() {
        try {
            int actual1 = game.getCount2();
            int expected1 = 0;
            assertEquals(expected1, actual1);
            game.setTime();
            TimeUnit.SECONDS.sleep(10);
            actual1 = game.getCount2();
            expected1 = 0;
            assertEquals(expected1, actual1);
            game.setTime();
            TimeUnit.SECONDS.sleep(10);
            actual1 = game.getCount2();
            expected1 = 1;
            assertEquals(expected1, actual1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}