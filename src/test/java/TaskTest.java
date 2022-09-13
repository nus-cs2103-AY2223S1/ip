import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskTest {
    @Test
    public void load_unmarkedTodo_success() {
        Task task = Task.loadTask("T | 0 | 0 | Tutorial 1");
        Todo todo = new Todo("Tutorial 1");
        assertEquals(todo.toString(), task.toString());
    }

    @Test
    public void load_markedTodo_success() {
        Task task = Task.loadTask("T | 1 | 0 | Tutorial 1");
        Todo todo = new Todo("Tutorial 1");
        todo.mark();
        assertEquals(todo.toString(), task.toString());
    }

    @Test
    public void load_unmarkedDeadline_success() {
        Task task = Task.loadTask("D | 0 | 0 | Tutorial 1 | 2022-08-25");
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals(deadline.toString(), task.toString());
    }

    @Test
    public void load_markedDeadline_success() {
        Task task = Task.loadTask("D | 1 | 0 | Tutorial 1 | 2022-08-25");
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        deadline.mark();
        assertEquals(deadline.toString(), task.toString());
    }

    @Test
    public void load_unmarkedEvent_success() {
        Task task = Task.loadTask("E | 0 | 0 | Tutorial 1 | 2022-08-25");
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals(event.toString(), task.toString());
    }

    @Test
    public void load_markedEvent_success() {
        Task task = Task.loadTask("E | 1 | 0 | Tutorial 1 | 2022-08-25");
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        event.mark();
        assertEquals(event.toString(), task.toString());
    }
}
