package dukeegg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Todo;

public class TaskListTest {
    @Test
    public void add_normalTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("todo", true));
        assertEquals(1, taskList.size());
    }
}
