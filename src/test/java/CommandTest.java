import duke.exceptions.DukeException;
import duke.exceptions.TaskNotFoundException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.TaskStorage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.util.Ui;
import duke.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandTest {
    @Test
    public void testCommand() {
        try {
            TaskStorage storage = new TaskStorage("data/Tasks.txt");
            Command tester = new Command(storage, storage.loadTask(), new Ui());
            tester.execute(new ArrayList<String>(Arrays.asList("asdfasdfasdfas")));
        } catch (DukeException e) {
            assertEquals("Invalid input", e.getMessage());
        }
    }
}
