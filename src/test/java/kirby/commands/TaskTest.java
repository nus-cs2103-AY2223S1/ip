package kirby.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.ui.Ui;

public class TaskTest {
    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    public TaskTest() throws IOException {
        this.storage = new Storage("data/blank.txt", "data/");
        this.ui = new Ui();
        taskList = new TaskList(null);
    }

    @Test
    public void checkNoArguments() {
        try {
            String[] emptyArr = {null, null, null};
            new DeadlineCommand(emptyArr).execute(taskList, ui, storage);
        } catch (KirbyMissingArgumentException e) {
            String expectedOutput = "Aaah you have missing arguments! " + "deadline command is invalid! \nTry again!";
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}
