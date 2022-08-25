package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        Task task = new Event("project meeting", LocalDate.parse("2022-11-02"));
        assertEquals("[E][ ] project meeting (at: Nov 02 2022)", task.toString());
        task.markAsDone();
        assertEquals("[E][X] project meeting (at: Nov 02 2022)", task.toString());
    }
}
