import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskTest {
    @Test
    public void loadTodoTest() {
        Task task = Task.loadTask("T | 0 | Tutorial 1");
        Todo todo = new Todo("Tutorial 1");
        assertEquals(todo.toString(), task.toString());
    }

    @Test
    public void loadTodoTest2() {
        Task task = Task.loadTask("T | 1 | Tutorial 1");
        Todo todo = new Todo("Tutorial 1");
        todo.mark();
        assertEquals(todo.toString(), task.toString());
    }

    @Test
    public void loadDeadlineTest() {
        Task task = Task.loadTask("D | 0 | Tutorial 1 | 2022-08-25");
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals(deadline.toString(), task.toString());
    }

    @Test
    public void loadDeadlineTest2() {
        Task task = Task.loadTask("D | 1 | Tutorial 1 | 2022-08-25");
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        deadline.mark();
        assertEquals(deadline.toString(), task.toString());
    }

    @Test
    public void loadEventTest() {
        Task task = Task.loadTask("E | 0 | Tutorial 1 | 2022-08-25");
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals(event.toString(), task.toString());
    }

    @Test
    public void loadEventTest2() {
        Task task = Task.loadTask("E | 1 | Tutorial 1 | 2022-08-25");
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        event.mark();
        assertEquals(event.toString(), task.toString());
    }
}
