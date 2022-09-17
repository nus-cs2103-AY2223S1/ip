package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testMarkEvent(){
        Event event = new Event("laundry","12/12/2022 1500");
        assertEquals("[E][ ] laundry (by: Monday, 12 December 2022)",event.toUser());
        event.markAsDone();
        assertEquals("[E][X] laundry (by: Monday, 12 December 2022)",event.toUser());
    }

    @Test
    public void testUnmarkEvent(){
        Event event = new Event("laundry","12/12/2022 1500");
        assertEquals("[E][ ] laundry (by: Monday, 12 December 2022)",event.toUser());
        event.markAsDone();
        assertEquals("[E][X] laundry (by: Monday, 12 December 2022)",event.toUser());
        event.markAsUndone();
        assertEquals("[E][ ] laundry (by: Monday, 12 December 2022)",event.toUser());

    }
}