package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/*import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;*/

public class TodoCommandTest {


    /*@Test
    public void executeTest() throws DukeException {
        String actualOutput = "+ Added this todo:\ntest\nNow you have 1 tasks in the list\n";
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.txt");
        TodoCommand command = new TodoCommand("test");
        command.execute(taskList, ui, storage);
        assertEquals(command.execute(taskList, ui, storage), actualOutput);
    }*/

    @Test
    public void isExitTest() {
        TodoCommand command = new TodoCommand("test");
        assertEquals(command.isExit(), false);
    }
}
