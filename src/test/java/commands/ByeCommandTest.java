package commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dukeegg.Storage;
import dukeegg.TaskList;
import ui.Ui;

public class ByeCommandTest {
    @Test
    public void execute_programWillTerminate() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./");
        Command command = new ByeCommand();

        assertTrue(command.isExit());
    }
}
