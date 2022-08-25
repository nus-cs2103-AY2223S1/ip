package duke;

import duke.exception.DukeException;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {

    @Test
    public void toString_newToDo_success() {
        ToDo toDo = new ToDo("test1");
        assertEquals("[T][ ] test1", toDo.toString());
    }

    @Test
    public void toStorage_newToDo_success() {
        ToDo toDo = new ToDo("test1");
        assertEquals("T | 0 | test1", toDo.toStorage());
    }
}
