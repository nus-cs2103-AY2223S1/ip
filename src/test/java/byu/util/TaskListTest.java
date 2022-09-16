package byu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import byu.exceptions.DuplicateException;
import byu.exceptions.InvalidIndexException;
import byu.task.Task;
import byu.task.ToDo;

public class TaskListTest {

    @Test
    public void addTask_noDuplicates_success() throws DuplicateException {
        TaskList list = new TaskList();
        Task t = new ToDo("sleep");
        list.addTask(t);
        assertEquals(list.getTask(1).getName(), "sleep");
    }

    @Test
    public void addTask_duplicateExists_exceptionThrown() {
        try {
            TaskList list = new TaskList();
            Task firstTask = new ToDo("sleep");
            Task secondTask = new ToDo("sleep");
            list.addTask(firstTask);
            list.addTask(secondTask);
        } catch (DuplicateException e) {
            assertEquals("The task already exists!", e.getMessage());
        }
    }

    @Test
    public void mark_validIndex_success() throws InvalidIndexException, DuplicateException {
        TaskList list = new TaskList();
        Task t = new ToDo("sleep");
        list.addTask(t);
        list.mark(1);
        assertTrue(t.isDone());
    }


    @Test
    public void mark_invalidIndex_exceptionThrown() throws DuplicateException {
        try {
            TaskList list = new TaskList();
            Task t = new ToDo("sleep");
            list.addTask(t);
            list.mark(2);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals("The task does not exist!", e.getMessage());
        }
    }
}
