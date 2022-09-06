package caca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import caca.exceptions.InvalidIndexException;

/**
 * Test class for TaskList.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class TaskListTest {

    /**
     * Tests the behaviour of addTask.
     */
    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList(null);

        taskList.addTask(new TaskStub("stub1"));
        taskList.addTask(new TaskStub("stub2"));
        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        assertEquals(taskList.getTasks().size(), 4);
    }

    /**
     * Tests the behaviour of deleteTask with a valid index.
     *
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    @Test
    public void deleteTask_validIndex_success() throws InvalidIndexException {
        TaskList taskList = new TaskList(null);

        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub("stub3"));
        taskList.addTask(new TaskStub("stub4"));
        assertEquals(taskList.getTasks().size(), 4);

        TaskList.indexOperation("delete", "3");
        assertEquals(taskList.getTasks().size(), 3);
    }

    /**
     * Tests the behaviour of deleteTask with an invalid index that is out of range.
     */
    @Test
    public void deleteTask_outOfRangeIndex_exceptionThrown() {
        TaskList taskList = new TaskList(null);

        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        assertEquals(taskList.getTasks().size(), 3);

        try {
            TaskList.indexOperation("delete", "4");
            assertEquals(taskList.getTasks().size(), 2);
            fail(); // Test should not reach this line.
        } catch (InvalidIndexException e) {
            assertEquals("OOPS!!! You have entered an invalid task index. It should be between 1 and 3.",
                    e.getMessage());
        }

    }

    /**
     * Tests the behaviour of deleteTask with an invalid index that is not a number.
     */
    @Test
    public void deleteTask_nonNumberIndex_exceptionThrown() {
        TaskList taskList = new TaskList(null);

        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        assertEquals(taskList.getTasks().size(), 3);

        try {
            TaskList.indexOperation("delete", "hi");
            assertEquals(taskList.getTasks().size(), 2);
            fail(); // Test should not reach this line.
        } catch (InvalidIndexException e) {
            assertEquals("OOPS!!! You have entered an invalid task index. It must be a number.",
                    e.getMessage());
        }

    }

    /**
     * Tests the behaviour of deleteTask without specifying an index.
     */
    @Test
    public void deleteTask_noIndexEntered_exceptionThrown() {
        TaskList taskList = new TaskList(null);

        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        assertEquals(taskList.getTasks().size(), 3);

        try {
            TaskList.indexOperation("delete", null);
            assertEquals(taskList.getTasks().size(), 2);
            fail(); // Test should not reach this line.
        } catch (InvalidIndexException e) {
            assertEquals("OOPS!!! Task index cannot be empty. "
                            + "It must be specified and must be a number.",
                    e.getMessage());
        }

    }
}
