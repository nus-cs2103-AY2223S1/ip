package duke.chatbot.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.chatbot.taskmanager.task.ToDoTask;

public class TaskManagerTest {
    @Test
    public void addTask() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.addTask(new ToDoTask("test")), "test");
    }

    @Test
    public void listTask() {
        TaskManager taskManager = new TaskManager();
        assertEquals(taskManager.listTask(), "");
        taskManager.addTask(new ToDoTask("test"));
        assertEquals(taskManager.listTask(), "1) [T][ ] test\n");
        taskManager.addTask(new ToDoTask("test 2"));
        assertEquals(taskManager.listTask(), "1) [T][ ] test\n2) [T][ ] test 2\n");
    }

    @Test
    public void markTask() {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new ToDoTask("test"));
        taskManager.addTask(new ToDoTask("test 2"));
        assertTrue(taskManager.markTask(1));
        assertTrue(taskManager.markTask(2));
        assertFalse(taskManager.markTask(1));
        assertFalse(taskManager.markTask(2));
    }

    @Test
    public void unmarkTask() {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new ToDoTask("test"));
        taskManager.addTask(new ToDoTask("test 2"));
        assertFalse(taskManager.unmarkTask(1));
        assertFalse(taskManager.unmarkTask(2));
    }

    @Test
    public void deleteTask() {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new ToDoTask("test"));
        taskManager.addTask(new ToDoTask("test 2"));
        assertEquals(taskManager.deleteTask(1), "[T][ ] test");
        assertEquals(taskManager.deleteTask(1), "[T][ ] test 2");

        taskManager.addTask(new ToDoTask("test"));
        taskManager.addTask(new ToDoTask("test 2"));
        assertEquals(taskManager.deleteTask(2), "[T][ ] test 2");
        assertEquals(taskManager.deleteTask(1), "[T][ ] test");

        taskManager.addTask(new ToDoTask("test"));
        taskManager.addTask(new ToDoTask("test 2"));
        taskManager.addTask(new ToDoTask("test 3"));
        assertEquals(taskManager.deleteTask(2), "[T][ ] test 2");
        assertEquals(taskManager.deleteTask(2), "[T][ ] test 3");
        assertEquals(taskManager.deleteTask(1), "[T][ ] test");

        taskManager.addTask(new ToDoTask("test"));
        taskManager.addTask(new ToDoTask("test 2"));
        taskManager.addTask(new ToDoTask("test 3"));
        assertEquals(taskManager.deleteTask(1), "[T][ ] test");
        assertEquals(taskManager.deleteTask(2), "[T][ ] test 3");
        assertEquals(taskManager.deleteTask(1), "[T][ ] test 2");
    }

    @Test
    public void findTask() {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new ToDoTask("test"));
        assertEquals(taskManager.findTask("test"), "1) [T][ ] test\n");

        taskManager.addTask(new ToDoTask("test 2"));
        assertEquals(taskManager.findTask("test"), "1) [T][ ] test\n2) [T][ ] test 2\n");

        taskManager.addTask(new ToDoTask("buy book"));
        taskManager.addTask(new ToDoTask("buy book 2"));
        assertEquals(taskManager.findTask("book"), "3) [T][ ] buy book\n4) [T][ ] buy book 2\n");
        assertEquals(taskManager.findTask("2"), "2) [T][ ] test 2\n4) [T][ ] buy book 2\n");
    }

    @Test
    public void updateTask() {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new ToDoTask("test"));
        assertEquals(taskManager.updateTask(1, "buy book"), "[T][ ] buy book");
        assertEquals(taskManager.updateTask(1, "read book"), "[T][ ] read book");
    }
}
