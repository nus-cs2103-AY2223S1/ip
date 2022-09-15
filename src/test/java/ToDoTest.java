import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class ToDoTest {
    @Test
    public void instantiateToDoTask_properDescription_success() {
        ToDo todo = new ToDo("read book pages 123-125",
                true, LocalDateTime.now());
        assertEquals("[T][X] read book pages 123-125", todo.toString());
    }

    @Test
    public void saveStringFormat_withDeliminators_success() {
        ToDo todo = new ToDo("| hello | this \\| is a \\\\| test");
        assertEquals("T | N | \\| hello \\| this \\\\| is a \\\\\\| test | null",
                todo.toSaveFormat());
    }
}
