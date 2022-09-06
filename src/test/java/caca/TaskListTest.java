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
     * Tests the behaviour of addTask without duplicates.
     */
    @Test
    public void addTaskTest_withoutDuplicates_success() {
        TaskList taskList = new TaskList(null);

        TaskList.addTask(new TaskStub("stub1"));
        TaskList.addTask(new TaskStub("stub2"));
        TaskList.addTask(new TaskStub(null));
        assertEquals(3, taskList.getTasks().size());
    }

    /**
     * Tests the behaviour of addTask with duplicates.
     */
    @Test
    public void addTaskTest_withDuplicates_warning() {
        TaskList taskList = new TaskList(null);

        TaskStub task = new TaskStub("stub1");
        TaskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());

        TaskStub duplicateTask = new TaskStub("stub1");
        String addDuplicateTask = TaskList.addTask(duplicateTask);
        assertEquals(1, taskList.getTasks().size());

        assertEquals("OOPS!!!\n"
                        + "Your task list already contains [ ][ ] stub1!\n"
                        + "This is not added again.",
                addDuplicateTask);
    }

    /**
     * Tests the behaviour of deleteTask with a valid index.
     *
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    @Test
    public void deleteTask_validIndex_success() throws InvalidIndexException {
        TaskList taskList = new TaskList(null);

        TaskList.addTask(new TaskStub("stub1"));
        TaskList.addTask(new TaskStub("stub2"));
        assertEquals(2, taskList.getTasks().size());

        TaskList.indexOperation("delete", "2");
        assertEquals(1, taskList.getTasks().size());
    }

    /**
     * Tests the behaviour of deleteTask with an invalid index that is out of range.
     */
    @Test
    public void deleteTask_outOfRangeIndex_exceptionThrown() {
        TaskList taskList = new TaskList(null);

        TaskList.addTask(new TaskStub("stub 1"));
        TaskList.addTask(new TaskStub("stub 2"));
        TaskList.addTask(new TaskStub("stub 3"));
        assertEquals(3, taskList.getTasks().size());

        try {
            TaskList.indexOperation("delete", "4");
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

        TaskList.addTask(new TaskStub("stub 1"));
        TaskList.addTask(new TaskStub("stub 2"));
        TaskList.addTask(new TaskStub("stub 3"));
        assertEquals(3, taskList.getTasks().size());

        try {
            TaskList.indexOperation("delete", "hi");
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

        TaskList.addTask(new TaskStub("stub 1"));
        TaskList.addTask(new TaskStub("stub 2"));
        TaskList.addTask(new TaskStub("stub 3"));
        assertEquals(3, taskList.getTasks().size());

        try {
            TaskList.indexOperation("delete", null);
            fail(); // Test should not reach this line.
        } catch (InvalidIndexException e) {
            assertEquals("OOPS!!! Task index cannot be empty. "
                            + "It must be specified and must be a number.",
                    e.getMessage());
        }

    }
}
