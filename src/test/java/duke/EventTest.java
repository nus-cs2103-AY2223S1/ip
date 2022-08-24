package duke;

import org.junit.jupiter.api.Test;
import task.Event;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void addEventTest() {
        Event event = new Event("Add Event", LocalDate.parse("2022-08-08"));
        assertEquals("[E][ ] Add Event (at: Aug 8 2022)", event.toString());
    }

    @Test
    public void saveEventTest() {
        Event event = new Event("Add Event", LocalDate.parse("2022-08-08"));
        assertEquals("E##N##Add Event##2022-08-08", event.stringify());
    }
}