import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class TaskListTest {
    @Test
    public void markEventTest() {
        Event event = new Event("Meeting", LocalDate.parse("2022-08-24"));
        event.markDone();
        assertEquals("[E] [X] Meeting (at: Aug 24 2022)", event.toString());
    }

    @Test
    public void unmarkDeadlineTest() {
        Deadline deadline = new Deadline("Homework", LocalDate.parse("2022-08-24"));
        deadline.markDone();
        deadline.markUndone();
        assertEquals("[D] [ ] Homework (by: Aug 24 2022)", deadline.toString());
    }

    @Test
    public void saveMarkedToDoTest() {
        ToDo todo = new ToDo("Essay");
        todo.markDone();
        assertEquals("T | 1 | Essay", todo.saveStringFormat());
    }
}
