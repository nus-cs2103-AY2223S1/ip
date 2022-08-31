package pikachu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getNameTest(){
        assertEquals("T", new Todo("").getName());
    }

    @Test
    public void getTimingTest(){
        assertEquals("", new Todo("").getTiming());
    }
}
