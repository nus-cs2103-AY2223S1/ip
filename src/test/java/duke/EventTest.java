package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.task.Event;
import duke.task.Priority;
import duke.task.TaskType;

public class EventTest {
    private Event initializeEvent() {
        return new Event("club meeting", LocalDate.of(2022, 10, 12),
                LocalTime.of(16, 30), LocalTime.of(20, 30), TaskType.EVENT, Priority.HIGH);
    }
    @Test
    public void isDateEqual_success() {
        assertFalse(initializeEvent().isDateEqual(LocalDate.of(2022, 10, 15)));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ][H] club meeting (on: 16:30 to 20:30 Oct 12 2022)", initializeEvent().toString());
    }

    @Test
    public void testStringConversionWithChangedPriority() {
        Event event = initializeEvent();
        event.setPriority(Priority.MEDIUM);
        assertEquals("[E][ ][M] club meeting (on: 16:30 to 20:30 Oct 12 2022)", event.toString());
    }

    @Test
    public void testFindDescriptionSuccess() {
        boolean isPresent = initializeEvent().isQueriesPresent("meeting", "lab");
        assertTrue(isPresent);
    }

    @Test
    public void testCannotFindDescriptionSuccess() {
        boolean isPresent = initializeEvent().isQueriesPresent("lab", "book");
        assertFalse(isPresent);
    }
}
