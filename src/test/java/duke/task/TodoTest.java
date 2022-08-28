package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TodoTest {

    @Test
    public void toString_newToDo_returnsString() {
        ToDo todo = new ToDo("Test");
        assertEquals("[T][ ] Test", todo.toString());
    }

    @Test
    public void toStorageString_newToDo_returnsStorageString() throws DukeException {
        ToDo todo = new ToDo("Test");
        assertEquals("T | 0 | Test", todo.toStorageString());
    }
}
