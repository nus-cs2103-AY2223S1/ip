package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void initializeTaskList() {
        this.taskList = new TaskList();
        this.taskList.addTask(new Todo("Test"));
    }

    @Test
    public void addTask_todoString_successfullyAdded() {
        this.taskList.addTask(new Todo("Test 2"));
        Assertions.assertEquals(2, this.taskList.getSize());
    }

    @Test
    public void removeTask_deleteString_correctlyRemoved() {
        try {
            this.taskList.removeTask(1);
            Assertions.assertEquals(0, this.taskList.getSize());
        } catch (Exception e) {
            Assertions.fail("No exception should have occurred");
        }
    }
}
