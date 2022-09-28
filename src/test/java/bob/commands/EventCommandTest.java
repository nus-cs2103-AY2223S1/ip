package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.BobException;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class EventCommandTest {
    @Test
    public void updateTask_incorrectDateFormat_exceptionThrown() {
        try {
            Command command = new EventCommand("assignment", "12-12-2022");
            TaskList tasks = new TaskList();
            Storage storage = new Storage();
            Ui ui = new Ui();
            command.executeCommand(tasks, storage, ui);
        } catch (BobException e) {
            assertEquals("please input date in yyyy-mm-dd format!", e.getMessage());
        }
    }
}
