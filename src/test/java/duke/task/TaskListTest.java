package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.constant.PriorityLevel;
import duke.exception.DukeException;

public class TaskListTest {
    private TaskList testTasks;

    @BeforeEach
    public void setUp() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        tasks.add(new TaskStub());
        tasks.add(new TaskStub());
        testTasks = tasks;
    }

    @Test
    public void getSize_getCorrectNumberOfTasks() {
        assertEquals(testTasks.getSize(), 3);
    }

    @Test
    public void toString_getCorrectString() {
        String expected = "1. test task\n2. test task\n3. test task\n";

        assertEquals(expected, testTasks.toString());
    }

    @Test
    public void toSaveFileString_getCorrectString() {
        String expected = "saveFileString\nsaveFileString\nsaveFileString\n";

        assertEquals(expected, testTasks.toSaveFileString());
    }

    @Test
    public void addTask_addSingleTask_getCorrectNumberOfTasks() {
        testTasks.add(new TaskStub());

        assertEquals(testTasks.getSize(), 4);
    }

    @Test
    public void addTask_addMultipleTasks_getCorrectNumberOfTasks() {
        for(int i = 0; i < 66; ++i) {
            testTasks.add(new TaskStub());
        }

        assertEquals(testTasks.getSize(), 69);
    }

    @Test
    public void deleteTask_negativeIndex_throwException() {
        try {
            testTasks.delete(-1);
            fail();
        } catch (DukeException e) {
            //pass;
        }
    }

    @Test
    public void deleteTask_indexTooLarge_throwException() {
        try {
            testTasks.delete(983948);
            fail();
        } catch (DukeException e) {
            //pass
        }
    }

    @Test
    public void deleteTask_validIndex_getCorrectNumberOfTasks() {
        try {
            testTasks.delete(1);
            assertEquals(testTasks.getSize(), 2);
        } catch (DukeException e) {
            fail();
        }
    }
}
