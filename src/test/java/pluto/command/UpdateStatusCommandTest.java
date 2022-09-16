package pluto.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;
import pluto.task.Todo;

public class UpdateStatusCommandTest {

    @Test
    public void execute_invalidIndex_exceptionThrown() throws IOException {
        try {
            Storage storage = new Storage("PlutoData.txt");
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            UpdateStatusCommand modifycmd = new UpdateStatusCommand(4, true);
            tasks.addTask(new Todo("sleep"));
            tasks.addTask(new Todo("study"));
            tasks.addTask(new Todo("eat"));
            try {
                modifycmd.execute(tasks, ui, storage);
                fail();
            } catch (PlutoException e) {
                assertEquals("OOPS!!! Valid index required.", e.getMessage());
                storage.rewriteFile(new TaskList());
            }
        } catch (PlutoException e) {
            assertEquals("OOPS!!! Couldn't find/create data file.", e.getMessage());
        }
    }
}
