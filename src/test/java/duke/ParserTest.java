package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.command.ToDoCommand;
import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.task.ToDo;

public class ParserTest {
    @Test
    public void testInputToDo1() {
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
    public void testInputToDo2() {
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
