package Duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskListTest {

    @Test
    public void add() {
            Task tempTask = new Task("");
            TaskList taskList = new TaskList();
            taskList.add(tempTask);
            assertNotNull(taskList);
    }
}
