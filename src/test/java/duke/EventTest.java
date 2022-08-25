package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    Event testEventOne = new Event("A test","test time");
    @Test
    public void testToString(){
        assertEquals("[E][ ] A test (at: test time)", testEventOne.toString());
    }

    @Test
    public void testEventGetter(){
        assertEquals("test time", testEventOne.getAt());
    }

}