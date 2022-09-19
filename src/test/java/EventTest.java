import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Event;

import java.time.LocalDate;


public class EventTest {
    @Test
    public void markEventTest(){
        Event event = new Event("buy bread", LocalDate.parse("2000-05-07"));
        event.markAsDone();
        assertEquals("[E][X]buy bread (at: May 7 2000)", event.toString());
    }

    @Test
    public void unmarkEventTest(){
        Event event = new Event("buy bread", LocalDate.parse("2000-05-07"));
        event.markAsDone();
        event.markAsUndone();
        assertEquals("[E][ ]buy bread (at: May 7 2000)", event.toString());
    }
}
