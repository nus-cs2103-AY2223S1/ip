package pikachu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getName_rightName(){
        assertEquals("T", new Todo("").getName());
    }

    @Test
    public void getTiming_rightNoTime(){
        assertEquals("", new Todo("").getTiming());
    }
}
