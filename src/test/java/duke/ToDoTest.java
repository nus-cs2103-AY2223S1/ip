package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

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
