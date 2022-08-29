package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void statusTest() throws DukeException {
        assertEquals("[T][ ] complete lab", makeToDo("todo complete lab").getStatus());
    }

    @Test
    public void saveFormatTest() throws DukeException {
        assertEquals("T | 0 | complete lab", makeToDo("todo complete lab").getTask());
    }

    @Test
    public void emptyDescriptionTest() {
        try {
            ToDo newTodo = makeToDo("");
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    public ToDo makeToDo(String input) throws DukeException {
        ToDo dummy = new ToDo();
        dummy.addName(input);
        return dummy;
    }
}
