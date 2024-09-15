package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("null", LocalDateTime.of(2022, 8, 8, 00, 00));
        event.markDone();
        assertEquals("[E][X] null (at: 12:00 am on 08/08/2022)", event.toString());
    }
}
