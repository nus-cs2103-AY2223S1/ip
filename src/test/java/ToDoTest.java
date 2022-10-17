package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import duke.task.ToDo;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("testing");
        assertEquals("T/ /testing", todo.toString());
    }

    @Test
    public void formatTask_success() {
        ToDo todo = new ToDo("testing");
        assertEquals("[T] [ ] testing", todo.formatTask());
    }
}