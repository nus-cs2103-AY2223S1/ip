package duke.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TaskListTest {

    @Test
    public void markTaskCorrectly() {
        TaskList taskList = new TaskList();
        Task task = new Todo("test");
        taskList.addNewTask(task);
        taskList.mark("mark", 1);
        assertTrue(task.isDone);
    }
}
