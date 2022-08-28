package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString(){
        assertEquals("[E][ ] birthday party (at: friday 6-11pm)",
                new Event("birthday party", false, "friday 6-11pm").toString());
    }

    @Test
    public void testGetDescription(){
        assertEquals("E | F | birthday party | friday 6-11pm\n",
                new Event("birthday party", false, "friday 6-11pm").getDescription());
    }
}
