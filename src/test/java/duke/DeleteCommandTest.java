package duke;

import command.Command;
import command.DeleteCommand;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommandTest {
    @Test
    public void execute_taskDeleted() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Todo("test 1"));
        tasks.add(new Todo("test 2"));
        TaskList taskList = new TaskList(tasks);
        Command c = new DeleteCommand(1);
        c.execute(taskList, new Ui(), new Storage("bin/tests.txt", "bin/testsArchive.txt"));
        assertEquals(1, taskList.getSize());
    }
}
