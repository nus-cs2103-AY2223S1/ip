package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.TaskType;
import duke.task.ToDo;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] read book", new ToDo("read book", TaskType.TODO).toString());
    }

    @Test
    public void isDateEqual_success() {
        assertFalse(new ToDo("read book", TaskType.TODO).isDateEqual(LocalDate.now()));
    }
}
