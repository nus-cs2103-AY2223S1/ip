package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests to test the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests if the getTask function works as intended, which should return the task at the specified index.
     */
    @Test
    public void getTask_getFirstTodoTask_todoTaskReturned() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        assertEquals("description", tasks.getTask(0).getDescription());
    }

    /**
     * Tests if the getTotalTasks function works as intended, which should return the total number of tasks
     * in the list.
     */
    @Test
    public void getTotalTasks_oneTaskTotal_oneReturned() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        assertEquals(1, tasks.getTotalTasks());
    }
}
