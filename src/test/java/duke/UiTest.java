
package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.dukeexception.DateTimeFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;

public class UiTest {
    @Test
    public void testAddEvent() throws DateTimeFormatException {
        Event event = Ui.addEvent("Job Application /at 2022-06-29 12:26");
        assertEquals("[E][ ] Job Application (at: Jun 29 2022 12:26)", event.toString().trim().substring(2));
    }

    @Test
    public void testMark() throws DateTimeFormatException {
        Deadline deadline = Ui.addDeadline("Random Task /by 2022-06-30");
        assertEquals("[D][ ] Random Task (by: Jun 30 2022)", deadline.toString().trim().substring(2));
        Ui.mark(deadline);
        assertEquals("     1.[D][X] Random Task (by: Jun 30 2022)", deadline.toString());
    }
}
