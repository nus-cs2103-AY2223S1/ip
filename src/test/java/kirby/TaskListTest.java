package kirby;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void createTaskList() {
        TaskList taskList = new TaskList(null);
        assertEquals(taskList.getList().size(), 0);
        taskList.addTask(new TaskStub(null));
        assertEquals(taskList.getList().size(), 1);
    }

    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub("stub"));
        assertEquals(taskList.getList().size(), 2);
    }

    @Test
    public void removeTaskTest() {
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TaskStub("task1"));
        taskList.addTask(new TaskStub("task2"));
        taskList.addTask(new TaskStub("task3"));
        assertEquals(taskList.getList().size(), 3);
        taskList.removeTask(2);
        assertEquals(taskList.getList().size(), 2);
    }
}
