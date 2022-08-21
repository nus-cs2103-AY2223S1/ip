package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void getTask_getFirstTodoTask_todoTaskReturned() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        assertEquals("description", tasks.getTask(0).getDescription());
    }

    @Test
    public void getTotalTasks_oneTaskTotal_oneReturned() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        assertEquals(1, tasks.getTotalTasks());
    }
}
