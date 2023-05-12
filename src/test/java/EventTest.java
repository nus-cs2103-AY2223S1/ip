import duke.Deadline;
import duke.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void newDeadlineTest() {
        Event event = new Event("finish homework", "2019-12-01 to 2019-12-03");
        assertEquals(event.toString(), "[E][ ] finish homework (at: 2019-12-01 to 2019-12-03)");
    }

    @Test
    public void markDeadlineTest() {
        Event event = new Event("finish homework", "2019-12-01 to 2019-12-03");
        event.markAsDone();
        assertEquals(event.toString(), "[E][X] finish homework (at: 2019-12-01 to 2019-12-03)");
    }
}
