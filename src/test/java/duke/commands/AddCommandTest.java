package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.ToDo;



public class AddCommandTest {

    private final AddCommand addToDo = new AddCommand(new ToDo("submit iP week 3"));

    public AddCommandTest() throws DukeException {
    }

    @Test
    public void addCommandTest() {
        assertEquals("[T][ ] submit iP week 3", addToDo.getTask().toString());
    }
}
