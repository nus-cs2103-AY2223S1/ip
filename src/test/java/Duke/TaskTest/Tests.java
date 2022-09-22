package Duke.TaskTest;
import cinnamon.Tasks.Deadline;
import cinnamon.Tasks.Event;
import cinnamon.Tasks.Todo;
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
        Deadline dl = new Deadline("essay", "2007-07-07", "1900");
        assertEquals("[D][ ] essay (by: SATURDAY, Jul 07 2007, 07:00 pm)", dl.toString());
    }

    @Test
    public void testEvent() {
        Event event = new Event("KTV",  "Vivocity","2007-04-04", "1400");
        assertEquals("[E][ ] KTV (at: Vivocity, WEDNESDAY, Apr 04 2007, 02:00 pm)", event.toString());
    }
}
