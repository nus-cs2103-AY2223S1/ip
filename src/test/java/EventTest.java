import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import task.Task;
import task.Event;
import task.Deadline;

public class EventTest {

    @Test
    public void getStatusIcon_unmarked() {
        Task event = new Event("read book", "20/10/2022 1800");
        event.isMark(false);
        assertEquals(" ", event.getStatusIcon());
    }

    @Test
    public void getStatusIcon_marked() {
        Task event = new Event("read book", "20/10/2022 1800");
        event.isMark(true);
        assertEquals("X", event.getStatusIcon());
    }

    @Test
    public void toString_success() {
        Task event = new Event("read book", "20/10/2022 1800");
        assertEquals("[E][ ] read book (at: Oct 20 2022 18:00:00)", event.toString());
    }
}
