package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import duke.task.Event;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("testing", LocalDate.parse("2022-08-26"));
        assertEquals("E/ /testing/2022-08-26", event.toString());
    }

    @Test
    public void formatTask_success() {
        Event event = new Event("testing", LocalDate.parse("2022-08-26"));
        assertEquals("[E] [ ] testing (at: Aug 26 2022)", event.formatTask());
    }
}