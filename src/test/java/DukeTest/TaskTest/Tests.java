package DukeTest.TaskTest;
import Duke.Deadline;
import Duke.Event;
import Duke.Todo;
import Duke.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    public void testTodo() {
        Todo todo = new Todo("grocery shopping");
        assertEquals("[T][ ] grocery shopping", todo.toString());
    }

    @Test
    public void testDeadline() {
        Deadline dl = new Deadline("essay","7/07/2007", "1900");
        assertEquals("[D][ ] essay (by: SATURDAY, Jul 07 2007, 07:00 pm)", dl.toString());
    }

    @Test
    public void testEvent() {
        Event event = new Event("KTV", "Vivocity", "4/04/2007", "1400");
        assertEquals("[E][ ] KTV (at: Vivocity, WEDNESDAY, Apr 04 2007, 02:00 pm)", event.toString());
    }

}
