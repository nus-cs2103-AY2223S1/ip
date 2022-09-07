package duke.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.taskmanager.task.EmptyTask;
public class TaskManagerTest {
    @Test
    public void addTask() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.addTask(new EmptyTask()), "\t> Added: \n");
    }

    @Test
    public void list() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.listTask(), "\tYou have no tasks in your list.\n");
        taskManager.addTask(new EmptyTask());
        assertEquals(taskManager.listTask(), "\tI have your list of tasks displayed below:\n\t1) [ ] \n");
    }

    @Test
    public void mark() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.markTask(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.markTask(1), "\tThere is no such task!!\n");
        assertEquals(taskManager.markTask(-1), "\tThere is no such task!!\n");

        taskManager.addTask(new EmptyTask());
        assertEquals(taskManager.markTask(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.markTask(2), "\tThere is no such task!!\n");
        assertEquals(taskManager.markTask(-1), "\tThere is no such task!!\n");
        assertEquals(taskManager.markTask(1), "\tI've marked this task as done. Good Job!\n");
        assertEquals(taskManager.markTask(1), "\tThe task is already marked you dummy.\n");
    }

    @Test
    public void unmark() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.unmarkTask(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmarkTask(1), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmarkTask(-1), "\tThere is no such task!!\n");

        taskManager.addTask(new EmptyTask());
        assertEquals(taskManager.unmarkTask(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmarkTask(2), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmarkTask(-1), "\tThere is no such task!!\n");
        assertEquals(taskManager.unmarkTask(1), "\tThe task is still not done you idiot.\n");
        taskManager.markTask(1);
        assertEquals(taskManager.unmarkTask(1), "\tThe task has been unmarked.\n");
        assertEquals(taskManager.unmarkTask(1), "\tThe task is still not done you idiot.\n");
    }

    @Test
    public void delete() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.deleteTask(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.deleteTask(1), "\tThere is no such task!!\n");
        assertEquals(taskManager.deleteTask(-1), "\tThere is no such task!!\n");

        taskManager.addTask(new EmptyTask());
        assertEquals(taskManager.deleteTask(0), "\tThere is no such task!!\n");
        assertEquals(taskManager.deleteTask(2), "\tThere is no such task!!\n");
        assertEquals(taskManager.deleteTask(-1), "\tThere is no such task!!\n");
        assertEquals(taskManager.deleteTask(1), "The following item has been removed.\n[ ] \n"
                + "You have 0 item(s) remaining.\n");
        assertEquals(taskManager.deleteTask(1), "\tThere is no such task!!\n");
    }
}
