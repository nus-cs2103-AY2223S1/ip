package duke.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.chatbot.commands.exceptions.EmptyTaskException;
import duke.taskmanager.task.ToDoTask;
public class TaskManagerTest {
    @Test
    public void addTask() {
        try {
            TaskManager taskManager = new TaskManager();
            assertEquals(taskManager.addTask(new ToDoTask("test")), "> Added: test\n");
        } catch (EmptyTaskException exception) {
            Assertions.fail();
        }
    }

    @Test
    public void list() {
        try {
            TaskManager taskManager = new TaskManager();
            assertEquals(taskManager.listTask(), "You have no tasks in your list.\n");
            taskManager.addTask(new ToDoTask("test"));
            assertEquals(taskManager.listTask(), "I have your list of tasks displayed below:\n1) [T][ ] test\n");
        } catch (EmptyTaskException exception) {
            Assertions.fail();
        }
    }

    @Test
    public void mark() {
        try {
            TaskManager taskManager = new TaskManager();
            assertEquals(taskManager.markTask(0), "There is no such task!!\n");
            assertEquals(taskManager.markTask(1), "There is no such task!!\n");
            assertEquals(taskManager.markTask(-1), "There is no such task!!\n");

            taskManager.addTask(new ToDoTask("test"));
            assertEquals(taskManager.markTask(0), "There is no such task!!\n");
            assertEquals(taskManager.markTask(2), "There is no such task!!\n");
            assertEquals(taskManager.markTask(-1), "There is no such task!!\n");
            assertEquals(taskManager.markTask(1), "I've marked this task as done. Good Job!\n");
            assertEquals(taskManager.markTask(1), "The task is already marked you dummy.\n");
        } catch (EmptyTaskException exception) {
            Assertions.fail();
        }
    }

    @Test
    public void unmark() {
        try {
            TaskManager taskManager = new TaskManager();
            assertEquals(taskManager.unmarkTask(0), "There is no such task!!\n");
            assertEquals(taskManager.unmarkTask(1), "There is no such task!!\n");
            assertEquals(taskManager.unmarkTask(-1), "There is no such task!!\n");

            taskManager.addTask(new ToDoTask("test"));
            assertEquals(taskManager.unmarkTask(0), "There is no such task!!\n");
            assertEquals(taskManager.unmarkTask(2), "There is no such task!!\n");
            assertEquals(taskManager.unmarkTask(-1), "There is no such task!!\n");
            assertEquals(taskManager.unmarkTask(1), "The task is still not done you idiot.\n");
            taskManager.markTask(1);
            assertEquals(taskManager.unmarkTask(1), "The task has been unmarked.\n");
            assertEquals(taskManager.unmarkTask(1), "The task is still not done you idiot.\n");
        } catch (EmptyTaskException exception) {
            Assertions.fail();
        }
    }

    @Test
    public void delete() {
        try {
            TaskManager taskManager = new TaskManager();
            assertEquals(taskManager.deleteTask(0), "There is no such task!!\n");
            assertEquals(taskManager.deleteTask(1), "There is no such task!!\n");
            assertEquals(taskManager.deleteTask(-1), "There is no such task!!\n");

            taskManager.addTask(new ToDoTask("test"));
            assertEquals(taskManager.deleteTask(0), "There is no such task!!\n");
            assertEquals(taskManager.deleteTask(2), "There is no such task!!\n");
            assertEquals(taskManager.deleteTask(-1), "There is no such task!!\n");
            assertEquals(taskManager.deleteTask(1), "The following item has been removed.\n[T][ ] test\n"
                    + "You have 0 item(s) remaining.\n");
            assertEquals(taskManager.deleteTask(1), "There is no such task!!\n");
        } catch (EmptyTaskException exception) {
            Assertions.fail();
        }
    }
}
