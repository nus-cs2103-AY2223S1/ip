package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInput;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MarkCommandTest {
    private final TaskList tasks;
    private Storage storage;
    private final Ui ui;

    public MarkCommandTest() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        try {
            this.storage = new Storage();
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void markTest_indexNotNumber() {
        try {
            Command command = new MarkCommand("-1", true);
            command.execute(tasks, ui, storage);
            fail("No exception thrown when index provided is not a number");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("Input is not a number").toString(), e.toString());
        }
    }

    @Test
    public void markTest_invalidIndex() {
        try {
            Command command = new MarkCommand("1", true);
            command.execute(tasks, ui, storage);
            fail("No exception thrown when invalid index provided");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("Please input a correct number").toString(), e.toString());
        }
    }

    @Test
    public void markTest_markSuccess() {
        try {
            tasks.add(new ToDo("test test"));
        } catch (DukeException e) {
            fail("Exception when adding test task");
        }
        try {
            Command commandMark = new MarkCommand("1", true);
            commandMark.execute(tasks, ui, storage);
            assertEquals("[T][X] test test", tasks.get(0).toString());
            Command commandUnmark = new MarkCommand("1", false);
            commandUnmark.execute(tasks, ui, storage);
            assertEquals("[T][ ] test test", tasks.get(0).toString());
        } catch (DukeException e) {
            fail(e);
        }
    }
}
