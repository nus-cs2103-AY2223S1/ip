package bro.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;
import bro.task.Todo;

/**
 * ModifyCommandTest class.
 */
public class ModifyCommandTest {
    /**
     * Checks whether the modifyCommand class works properly.
     */
    @Test
    public void execute_invalidIndex_returnsBroException() {
        Storage storage = new Storage("./bro.Bro.txt");
        TaskList tasklist = new TaskList();
        Ui ui = new Ui();
        ModifyCommand modifycmd = new ModifyCommand(ModifyCommand.ModifyType.MARK, 4);
        tasklist.todoTask(new Todo("party"), storage);
        tasklist.todoTask(new Todo("study"), storage);
        tasklist.todoTask(new Todo("eat"), storage);
        try {
            modifycmd.execute(tasklist, ui, storage);
            fail();
        } catch (BroException e) {
            assertEquals("Index is out of bound. Enter a valid index", e.getMessage());
        }
    }

}
