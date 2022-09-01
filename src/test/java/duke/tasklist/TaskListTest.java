package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    private TaskList taskList = new TaskList(new ArrayList<>());

    @Test
    public void getEmptyList() {
        assertEquals(0, taskList.getNumOfTasks());
    }

    @Test
    public void getReadTask() {
        taskList.addDeadline("watch lecture 1 /by 2022-08-27");
        assertEquals("[D][ ] watch lecture 1 (by: 2022-08-27)",
                taskList.readTask(0));
    }

    @Test
    public void getReadDescription() {
        taskList.addDeadline("watch lecture 1 /by 2022-08-27");
        assertEquals("watch lecture 1 ",
                taskList.getDescription(0));
    }
}
