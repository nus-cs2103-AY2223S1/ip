package duke.taskmanager;

import duke.taskmanager.task.EmptyTask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {
    @Test
    public void addTask() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.addTask(new EmptyTask()), "\t> Added: \n");
    }

    @Test
    public void list() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.list(), "\tYou have no tasks in your list.\n");
        taskManager.addTask(new EmptyTask());
        assertEquals(taskManager.list(), "\tI have your list of tasks displayed below:\n\t1) [ ] \n");
    }

    @Test
    public void mark() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.mark(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.mark(1), "\tThere is no such task!!\n");
        assertEquals(taskManager.mark(-1), "\tThere is no such task!!\n");

        taskManager.addTask(new EmptyTask());
        assertEquals(taskManager.mark(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.mark(2), "\tThere is no such task!!\n");
        assertEquals(taskManager.mark(-1), "\tThere is no such task!!\n");
        assertEquals(taskManager.mark(1), "\tI've marked this task as done. Good Job!\n");
        assertEquals(taskManager.mark(1), "\tThe task is already marked you dummy.\n");
    }

    @Test
    public void unmark() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.unmark(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmark(1), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmark(-1), "\tThere is no such task!!\n");

        taskManager.addTask(new EmptyTask());
        assertEquals(taskManager.unmark(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmark(2), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmark(-1), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmark(1), "\tThe task is still not done you idiot.\n");
        taskManager.mark(1);
        assertEquals(taskManager.unmark(1), "\tThe task has been unmarked.\n");
        assertEquals(taskManager.unmark(1), "\tThe task is still not done you idiot.\n");
    }

    @Test
    public void delete() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.delete(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.delete(1), "\tThere is no such task!!\n");
        assertEquals(taskManager.delete(-1), "\tThere is no such task!!\n");

        taskManager.addTask(new EmptyTask());
        assertEquals(taskManager.delete(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.delete(2), "\tThere is no such task!!\n");
        assertEquals(taskManager.delete(-1), "\tThere is no such task!!\n");
        assertEquals(taskManager.delete(1), "The following item has been removed.\n[ ] \nYou have 0 item(s) remaining.\n");
        assertEquals(taskManager.delete(1), "\tThere is no such task!!\n");
    }
}
