package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TaskListTest {
    @Test
    public void size_newTaskList_returnZero() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void size_nonEmptyTaskList_returnCorrectSize() {
        List<Task> tasks = new ArrayList<>();
        int numberOfTasksToAdd = 5;
        for (int i = 0; i < numberOfTasksToAdd; i++) {
            tasks.add(new Todo(Integer.toString(i)));
        }
        TaskList taskList = new TaskList(tasks);
        assertEquals(numberOfTasksToAdd, taskList.size());
    }

    @Test
    public void size_addMultipleTasks_returnCorrectSize() {
        TaskList taskList = new TaskList();
        int numberOfTasksToAdd = 3;
        for (int i = 0; i < numberOfTasksToAdd; i++) {
            taskList.addTask(new Todo(Integer.toString(i)));
        }
        assertEquals(numberOfTasksToAdd, taskList.size());
    }

    @Test
    public void addTask_newTask_taskAdded() {
        TaskList taskList = new TaskList();
        try {
            Task task = new Deadline("task", false, "2022-12-12");
            taskList.addTask(task);
            assertEquals(task, taskList.getTask(0));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getTask_indexInRange_returnTask() {
        TaskList taskList = new TaskList();
        Task zero = new Todo("zero");
        Task one = new Todo("one");
        taskList.addTask(zero);
        taskList.addTask(one);
        try {
            assertEquals(zero, taskList.getTask(0));
            assertEquals(one, taskList.getTask(1));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getTask_indexOutOfRange_dukeExceptionThrown() {
        TaskList taskList = new TaskList();
        Task zero = new Todo("zero");
        Task one = new Todo("one");
        taskList.addTask(one);
        taskList.addTask(zero);
        try {
            assertEquals(zero, taskList.getTask(10));
        } catch (DukeException e) {
            assertEquals("Task index out of range. Please choose from index 1 to 2",
                    e.getMessage());
        }
    }

    @Test
    public void deleteTask_deleteTask_taskDeleted() {
        TaskList taskList = new TaskList();
        Task task = new Todo("task");
        taskList.addTask(task);
        try {
            assertEquals(task, taskList.deleteTask(0));
            assertEquals(0, taskList.size());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markTask_taskNotDone_taskDone() {
        TaskList taskList = new TaskList();
        Task task = new Todo("task", false);
        taskList.addTask(task);
        try {
            taskList.markTask(0);
            assertEquals(true, taskList.getTask(0).isDone());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void unmarkTask_taskDone_taskNotDone() {
        TaskList taskList = new TaskList();
        Task task = new Todo("task", true);
        taskList.addTask(task);
        try {
            taskList.unmarkTask(0);
            assertEquals(false, taskList.getTask(0).isDone());
        } catch (DukeException e) {
            fail();
        }
    }
}
