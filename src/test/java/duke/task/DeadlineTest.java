package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void create_notDoneString_createdCorrectly() {
        Deadline deadline = Deadline.create("0", "abc", "2040-01-01T00:00");
        assertEquals("[D][ ] abc (by: 01 Jan 40 00:00)", deadline.toString());
    }

    @Test
    public void create_doneString_createdCorrectly() {
        Deadline deadline = Deadline.create("1", "abc", "2040-01-01T00:00");
        assertEquals("[D][X] abc (by: 01 Jan 40 00:00)", deadline.toString());
    }

    @Test
    public void fileFormat_notDone_formattedCorrectly() {
        Deadline deadline = Deadline.create("0", "abc", "2040-01-01T00:00");
        assertEquals("D | 0 | abc | 2040-01-01T00:00", deadline.getFileFormat());
    }

    @Test
    public void fileFormat_done_formattedCorrectly() {
        Deadline deadline = Deadline.create("1", "abc", "2040-01-01T00:00");
        assertEquals("D | 1 | abc | 2040-01-01T00:00", deadline.getFileFormat());
    }

    @Test
    public void markAsDone_done_changedCorrectly() {
        Deadline deadline = Deadline.create("0", "abc", "2040-01-01T00:00");
        deadline.markAsDone();
        assertEquals("[D][X] abc (by: 01 Jan 40 00:00)", deadline.toString());
    }

    @Test
    public void markAsUndone_undone_changedCorrectly() {
        Deadline deadline = Deadline.create("1", "abc", "2040-01-01T00:00");
        deadline.markAsUndone();
        assertEquals("[D][ ] abc (by: 01 Jan 40 00:00)", deadline.toString());
    }
}
