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

        TaskList.addTask(new TaskStub("stub 1"));
        TaskList.addTask(new TaskStub("stub 2"));
        TaskList.addTask(new TaskStub(null));
        assertEquals(3, taskList.getTasks().size());
    }

    /**
     * Tests the behaviour of addTask with duplicates.
     */
    @Test
    public void addTaskTest_withDuplicates_warning() {
        TaskList taskList = new TaskList(null);

        TaskStub task = new TaskStub("stub 1");
        TaskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());

        TaskStub duplicateTask = new TaskStub("stub 1");
        String addDuplicateTask = TaskList.addTask(duplicateTask);
        assertEquals(1, taskList.getTasks().size());

        assertEquals("OOPS!!! (*_*)\n"
                + "Duplicate task:\n"
                + "[ ][ ] stub 1\n"
                + "This is not added again.",
                addDuplicateTask);
    }

    /**
     * Tests the behaviour of deleting a task with a valid index.
     *
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    @Test
    public void deleteTask_validIndex_success() throws InvalidIndexException {
        TaskList taskList = new TaskList(null);

        TaskList.addTask(new TaskStub("stub 1"));
        TaskList.addTask(new TaskStub("stub 2"));
        assertEquals(2, taskList.getTasks().size());

        TaskList.indexOperation("delete", "2");
        assertEquals(1, taskList.getTasks().size());
    }

    /**
     * Tests the behaviour of deleting a task with an invalid index that is out of range.
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
            assertEquals("OOPS!!! (*_*)\n"
                    + "You have entered an invalid task index. It should be between 1 and 3.",
                    e.getMessage());
        }

    }

    /**
     * Tests the behaviour of deleting a task with an invalid index that is not a number.
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
            assertEquals("OOPS!!! (*_*)\n"
                    + "You have entered an invalid task index. It must be a number.",
                    e.getMessage());
        }

    }

    /**
     * Tests the behaviour of deleting a task without specifying an index.
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
            assertEquals("OOPS!!! (*_*)\n"
                    + "Task index cannot be empty. "
                    + "It must be specified and must be a number.",
                    e.getMessage());
        }

    }

    /**
     * Tests the behaviour of marking a task, which has not been marked yet, as done.
     *
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    @Test
    public void markTest_notMarkedYet_success() throws InvalidIndexException {
        TaskList taskList = new TaskList(null);

        TaskStub taskToMark = new TaskStub("stub 1");
        TaskList.addTask(taskToMark);
        assertEquals(1, taskList.getTasks().size());

        String actualOutput = TaskList.indexOperation("mark", "1");

        assertEquals("Nice! (> O <)\n"
                + "I've marked this task as done:\n[ ][X] stub 1", actualOutput);
    }

    /**
     * Tests the behaviour of marking a task, which has already been marked, as done.
     * This should lead to an error message.
     *
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    @Test
    public void markTest_alreadyMarked_warning() throws InvalidIndexException {
        TaskList taskList = new TaskList(null);

        TaskStub taskToMark = new TaskStub("stub 1");
        TaskList.addTask(taskToMark);
        assertEquals(1, taskList.getTasks().size());

        TaskList.indexOperation("mark", "1");
        String actualOutput = TaskList.indexOperation("mark", "1");

        assertEquals("OOPS!!! (*_*)\nYou have already completed this task!\n"
                + "But GOOD JOB (> O <)\n"
                + "You can enter \"delete 1\" to remove it.", actualOutput);
    }

    /**
     * Tests the behaviour of unmarking a task, which has not been unmarked yet.
     *
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    @Test
    public void unmarkTest_notUnmarkedYet_success() throws InvalidIndexException {
        TaskList taskList = new TaskList(null);

        TaskStub taskToUnmark = new TaskStub("stub 1");
        TaskList.addTask(taskToUnmark);
        assertEquals(1, taskList.getTasks().size());

        TaskList.indexOperation("mark", "1");
        String actualOutput = TaskList.indexOperation("unmark", "1");

        assertEquals("OK (O_O)\n"
                + "I've marked this task as not done yet:\n[ ][ ] stub 1", actualOutput);
    }

    /**
     * Tests the behaviour of unmarking a task, which has already been unmarked.
     * This should lead to an error message.
     *
     * @throws InvalidIndexException If task index is invalid, i.e. out of range.
     */
    @Test
    public void unmarkTest_alreadyUnmarked_warning() throws InvalidIndexException {
        TaskList taskList = new TaskList(null);

        TaskStub taskToUnmark = new TaskStub("stub 1");
        TaskList.addTask(taskToUnmark);
        assertEquals(1, taskList.getTasks().size());

        TaskList.indexOperation("mark", "1");
        TaskList.indexOperation("unmark", "1");
        String actualOutput = TaskList.indexOperation("unmark", "1");

        assertEquals("OOPS!!! (*_*)\n"
                + "You have not completed this task yet, so it is already unmarked!\n"
                + "You may want to start working on it now.\n"
                + "ALL THE BEST (^ O ^)", actualOutput);
    }
}
