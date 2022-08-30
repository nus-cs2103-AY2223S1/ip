package commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

public class MarkCommandTest {
    @Test
    public void execute_emptyTaskList_exceptionThrown() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./");

        String[] inputStrings = new String[]{"mark", "1"};
        Command command = new MarkCommand(inputStrings);

        assertThrows(DukeException.class, () -> command.execute(taskList, ui, storage));
    }
}
