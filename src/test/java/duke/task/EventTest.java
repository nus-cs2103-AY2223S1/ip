package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class EventTest {
    @Test
    public void getAtTest() {
        Event sampleEvent = new Event("Celebrate birthday", "2023-03-28");
        assertEquals(LocalDate.parse("2023-03-28"), sampleEvent.getAt());
    }

    @Test
    public void toStringTest() {
        Event sampleEvent = new Event("Celebrate birthday", "2023-03-28");
        assertEquals("[E][ ] Celebrate birthday (at: Mar 28 2023)", sampleEvent.toString());
    }
}