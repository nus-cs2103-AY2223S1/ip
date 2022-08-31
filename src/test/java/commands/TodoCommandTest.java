package commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dukeegg.Storage;
import dukeegg.TaskList;
import dukeegg.Ui;
import exceptions.DukeException;

public class TodoCommandTest {
    @Test
    public void execute_emptyDescription_exceptionThrown() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./");

        String[] inputStrings = new String[]{"todo"};
        Command command = new TodoCommand(inputStrings);

        assertThrows(DukeException.class, () -> command.execute(taskList, ui, storage));
    }
}
