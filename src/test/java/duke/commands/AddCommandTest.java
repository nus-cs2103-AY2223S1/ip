package duke.commands;

import duke.exception.DukeException;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    private AddCommand addToDo = new AddCommand(new ToDo("submit iP week 3"));

    public AddCommandTest() throws DukeException {
    }

    @Test
    public void AddCommandTest() {
        assertEquals("[T][ ] submit iP week 3", addToDo.toAdd.toString());
    }
}
