package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.command.ToDoCommand;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.Test;

import duke.command.Command;

public class ParserTest {
    @Test
    public void inputToDo_toDoHello_toDoCommandReturned() {
        Command received = null;
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        try {
            received = Parser.inputCommand("todo hello", taskList, ui);
        } catch (DukeException e) {
            assertEquals("Error: DukeException thrown", "Dummy actual");
        }
        assertEquals(received, new ToDoCommand("todo hello", taskList, ui));
    }

    @Test
    public void inputToDo_toDoToDo_toDoCommandReturned() {
        Command received = null;
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        try {
            received = Parser.inputCommand("todo todo", taskList, ui);
        } catch (DukeException e) {
            assertEquals("Error: DukeException thrown", "Dummy actual");
        }
        assertEquals(received, new ToDoCommand("todo todo", taskList, ui));
    }
}
