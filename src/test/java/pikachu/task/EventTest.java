package pikachu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getName_rightName(){
        assertEquals("E", new Event("","").getName());
    }

    @Test
    public void getLocation_rightLocation(){
        assertEquals("RC4", new Event("","RC4").getTiming());
    }
}
