package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void create_notDoneString_createdCorrectly() {
        Event event = Event.create("0", "abc", "2040-01-01T00:00");
        assertEquals("[E][ ] abc (at: 01 Jan 40 00:00)", event.toString());
    }

    @Test
    public void create_doneString_createdCorrectly() {
        Event event = Event.create("1", "abc", "2040-01-01T00:00");
        assertEquals("[E][X] abc (at: 01 Jan 40 00:00)", event.toString());
    }

    @Test
    public void fileFormat_notDone_formattedCorrectly() {
        Event event = Event.create("0", "abc", "2040-01-01T00:00");
        assertEquals("E | 0 | abc | 2040-01-01T00:00", event.getFileFormat());
    }

    @Test
    public void fileFormat_done_formattedCorrectly() {
        Event event = Event.create("1", "abc", "2040-01-01T00:00");
        assertEquals("E | 1 | abc | 2040-01-01T00:00", event.getFileFormat());
    }

    @Test
    public void markAsDone_done_changedCorrectly() {
        Event event = Event.create("0", "abc", "2040-01-01T00:00");
        event.markAsDone();
        assertEquals("[E][X] abc (at: 01 Jan 40 00:00)", event.toString());
    }

    @Test
    public void markAsUndone_undone_changedCorrectly() {
        Event event = Event.create("1", "abc", "2040-01-01T00:00");
        event.markAsUndone();
        assertEquals("[E][ ] abc (at: 01 Jan 40 00:00)", event.toString());
    }
}
