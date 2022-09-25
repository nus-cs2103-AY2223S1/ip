package Testing;

import org.junit.jupiter.api.Test;

import dukeprogram.facilities.TaskList;
import dukeprogram.tasks.Deadline;
import dukeprogram.tasks.Event;
import dukeprogram.tasks.Task;
import dukeprogram.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/**
 * Testcases that involve task list manipulation
 */
public class TaskListTests {

    /**
     * Checks if a task list is created correctly
     */
    @Test
    public void taskListCreation() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());
    }

    /**
     * Checks if a task list can be added to correctly
     */
    @Test
    public void taskList_addItemsCorrectly() {
        TaskList taskList = new TaskList();
        Task taskA = new ToDo("taskA");
        Task taskB = new ToDo("taskB");

        assertEquals(0, taskList.getSize());

        taskList.add(taskA);
        assertEquals(1, taskList.getSize());

        taskList.add(taskB);
        assertEquals(2, taskList.getSize());
        assertEquals(taskB, taskList.get(1));
    }

    /**
     * Checks if a task list can be removed correctly
     */
    @Test
    public void taskList_removesItemsCorrectly() {
        TaskList taskList = new TaskList();
        Task taskA = new ToDo("taskA");
        Task taskB = new ToDo("taskB");

        assertEquals(0, taskList.getSize());

        taskList.add(taskA);
        taskList.add(taskB);

        taskList.remove(0);
        assertEquals(taskB, taskList.get(0));
    }

    /**
     * Checks if removing beyond the task list size will correctly throw assertion errors
     * and guards the remove method correctly
     */
    @Test
    public void taskList_preventsExcessRemoval() {
        TaskList taskList = new TaskList();
        Task taskA = new ToDo("taskA");
        Task taskB = new ToDo("taskB");

        assertEquals(0, taskList.getSize());

        taskList.add(taskA);
        taskList.add(taskB);

        taskList.remove(0);
        taskList.remove(0);
        assertThrows(AssertionError.class, () -> taskList.remove(0));
    }

    /**
     * Checks if task list can add every event type correctly
     */
    @Test
    public void taskList_addsAllTypes() {
        TaskList taskList = new TaskList();
        Task taskA = new ToDo("taskA");
        Task taskB = new Event("taskB", "26 Sep", "30 Sep");
        Task taskC = new Deadline("taskC", "29 Sep");

        assertEquals(0, taskList.getSize());

        assertTrue(taskList.add(taskA));
        assertTrue(taskList.add(taskB));
        assertTrue(taskList.add(taskC));

        assertEquals(3, taskList.getSize());
    }

    /**
     * Prevents the addition of two same task objects
     */
    @Test
    public void taskList_preventedRepeatedAdditionsOfSameObject() {
        TaskList taskList = new TaskList();
        Task taskB = new Event("taskB", "26 Sep", "30 Sep");

        assertEquals(0, taskList.getSize());

        assertTrue(taskList.add(taskB));
        assertEquals(1, taskList.getSize());
        assertFalse(taskList.add(taskB));
        assertEquals(1, taskList.getSize());
    }

    /**
     * Checks if find will correctly return all tasks when provided with an empty string
     */
    @Test
    public void taskList_findsAllTask() {
        TaskList taskList = new TaskList();
        Task taskA = new ToDo("taskA");
        Task taskB = new Event("taskB", "26 Sep", "30 Sep");
        Task taskC = new Deadline("taskC", "29 Sep");

        taskList.add(taskA);
        taskList.add(taskB);
        taskList.add(taskC);
        assertArrayEquals(new Task[] { taskA, taskB, taskC }, taskList.findTasks(""));
    }

    /**
     * Checks if find will correctly return specific tasks by substring
     */
    @Test
    public void taskList_findsSpecific() {
        TaskList taskList = new TaskList();
        Task taskA = new ToDo("abcdef");
        Task taskB = new Event("abcef", "26 Sep", "30 Sep");
        Task taskC = new Deadline("acdef", "29 Sep");

        taskList.add(taskA);
        taskList.add(taskB);
        taskList.add(taskC);
        assertArrayEquals(new Task[] { taskA, taskB }, taskList.findTasks("abc"));
        assertArrayEquals(new Task[] { taskA, taskC }, taskList.findTasks("de"));
        assertArrayEquals(new Task[] { taskA, taskB }, taskList.findTasks("bc"));
        assertArrayEquals(new Task[] { taskA, taskB, taskC }, taskList.findTasks("ef"));
    }

    /**
     * Checks if the task list can clear correctly
     */
    public void taskList_clearsCorrectly() {
        TaskList taskList = new TaskList();
        Task taskA = new ToDo("abcdef");
        Task taskB = new Event("abcef", "26 Sep", "30 Sep");
        Task taskC = new Deadline("acdef", "29 Sep");

        taskList.add(taskA);
        taskList.add(taskB);
        taskList.add(taskC);

        assertEquals(3, taskList.getSize());
        taskList.clear();
        assertEquals(0, taskList.getSize());
    }
}
