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

        TaskList.deleteTask(String.valueOf(3));
        assertEquals(taskList.getTasks().size(), 3);
    }

    /**
     * Tests the behaviour of deleteTask with an invalid index.
     */
    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList(null);

        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        taskList.addTask(new TaskStub(null));
        assertEquals(taskList.getTasks().size(), 3);

        try {
            TaskList.deleteTask(String.valueOf(4));
            assertEquals(taskList.getTasks().size(), 2);
            fail(); // Test should not reach this line.
        } catch (InvalidIndexException e) {
            assertEquals("OOPS!!! You have entered an invalid task index. It should be between 1 and 3.",
                    e.getMessage());
        }

    }
}
