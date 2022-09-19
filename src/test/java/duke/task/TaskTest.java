package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void loadDeadlineTest() {
        Task task = Task.load("D | 0 | Read Book | 2069-06-09");
        Deadline deadline = new Deadline("Read Book", LocalDate.parse("2069-06-09"));
        assertEquals(deadline.toString(), task.toString());
    }

    @Test
    public void loadEventTest() {
        Task task = Task.load("E | 0 | Book Meet | 2069-06-09");
        Event event = new Event("Book Meet", LocalDate.parse("2069-06-09"));
        assertEquals(event.toString(), task.toString());
    }

    @Test
    public void loadToDoTest() {
        Task task = Task.load("T | 0 | Return Book");
        ToDo todo = new ToDo("Return Book");
        assertEquals(todo.toString(), task.toString());
    }
}
