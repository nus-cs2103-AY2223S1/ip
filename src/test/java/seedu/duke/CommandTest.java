package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class CommandTest {
    @Test
    public void addTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList tasklist = new TaskList(tasks);
        Ui ui = new Ui();
        Storage storage = new Storage("");
        Command command = Command.TODO;
        String input = "todo this";
        try {
            command.execute(tasklist, ui, storage, input);
        } catch (DukeException e) {

        }
        assertEquals(1, tasks.size());
    }

    @Test
    public void deleteTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("this"));
        TaskList tasklist = new TaskList(tasks);
        Ui ui = new Ui();
        Storage storage = new Storage("");
        Command command = Command.DELETE;
        String input = "delete 1";
        try {
            command.execute(tasklist, ui, storage, input);
        } catch (DukeException e) {

        }
        assertEquals(0, tasks.size());
    }

}
