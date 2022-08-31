package pikachu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getNameTest(){
        assertEquals("E", new Event("","").getName());
    }

    @Test
    public void getTimingTest(){
        assertEquals("RC4", new Event("","RC4").getTiming());
    }
}
