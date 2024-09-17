package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void taskList_getSize_correctSize() {
        TaskList testTaskList = new TaskList();
        Todo testTask1 = new Todo("todo Dummy1", false);
        Todo testTask2 = new Todo("todo Dummy2", false);
        Todo testTask3 = new Todo("todo Dummy3", false);
        testTaskList.addTask(testTask1);
        testTaskList.addTask(testTask2);
        testTaskList.addTask(testTask3);
        assertEquals(testTaskList.getSize(), 3);
    }

    @Test
    public void taskList_deleteTask_isDeleted() {
        TaskList testTaskList = new TaskList();
        Todo testTask1 = new Todo("todo Dummy1", false);
        Todo testTask2 = new Todo("todo Dummy2", false);
        Todo testTask3 = new Todo("todo Dummy3", false);
        testTaskList.addTask(testTask1);
        testTaskList.addTask(testTask2);
        testTaskList.addTask(testTask3);
        int prevSizeOfTaskList = testTaskList.getSize();
        try {
            testTaskList.deleteTask(1);
            int currSizeOfTaskList = testTaskList.getSize();
            assertEquals(currSizeOfTaskList, prevSizeOfTaskList - 1);
        } catch (TaskNotFoundException e) {
            fail("Unexpected exception thrown");
        }
    }
}
