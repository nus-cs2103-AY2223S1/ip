package duke;

import org.junit.jupiter.api.Test;
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
        taskList = c.execute(taskList, new Ui(), new Storage("bin/tests.txt"));
        assertEquals(1, taskList.getSize());
    }
}
