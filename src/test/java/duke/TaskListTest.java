package duke;

import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {


    @Test
    public void add() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task tempTask = new Todo("");
        tasks.add(tempTask);
        assertNotNull(tasks);
    }

    @Test
    public void isValidTaskNumbertest() {
        TaskList taskList = new TaskList();
        assertFalse(taskList.isValidTaskNumber(10));
    }
}

