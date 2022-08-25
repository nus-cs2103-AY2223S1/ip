package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event testEvent = new Event("lunch", "2022-02-03", "12:00", "14:00");
    @Test
    public void testToString(){
        assertEquals(testEvent.toString(), "[E][ ] lunch (at: Feb 3 2022, 1200PM to 0200PM)");
    }

    @Test
    public void testToFile(){
        assertEquals(testEvent.toFile(), "E|0|lunch|2022-02-03|12:00|14:00\n");
    }
}