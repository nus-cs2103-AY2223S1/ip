package blink.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.Task;

public class ByeCommandTest {

    @Test
    public void executeTest() {
        Command byeCommand = new ByeCommand();
        String expected = "";
        Ui ui = new Ui();
        ArrayList<Task> lst = new ArrayList<>();
        TaskList tasks = new TaskList(lst);
        Storage storage = new Storage(System.getProperty("user.home")
                + "/blink/blink.txt");
        assertEquals(expected, byeCommand.execute(tasks, ui, storage));
    }
}
