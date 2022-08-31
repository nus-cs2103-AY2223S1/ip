package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.Ui;
import duke.exception.DukeInvalidTimeException;
import duke.exception.DukeOutOfBoundException;

public class TaskListTest {
    private List<Task> list;
    private TaskList taskList;

    @BeforeEach
    public void initAll() {
        try {
            list = new ArrayList<>();
            taskList = new TaskList(list, new Ui());
            taskList.add(new ToDo("ToDo Test", "1"));
            taskList.add(new Deadline("Deadline Test", "2020-09-12 1200"));
            taskList.add(new Event("Event Test", "2020-09-12 1200"));
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void addTask_normalInput() {
        Task task = new Task("task", "D");
        taskList.add(task);
        assertEquals(4, taskList.getTaskList().size());
    }

    @Test
    public void deleteTask_normalInput() {
        try {
            taskList.delete(1);
            assertEquals(2, taskList.getTaskList().size());
        } catch (DukeOutOfBoundException e) {
            fail();
        }
    }

    @Test
    public void markTask_normalInput() {
        try {
            taskList.mark(1);
        } catch (DukeOutOfBoundException e) {
            fail();
        }
    }

    @Test
    public void unmarkTask_normalInput() {
        try {
            taskList.unmark(0);
        } catch (DukeOutOfBoundException e) {
            fail();
        }
    }

    @Test
    public void getTaskList_normalInput() {
        assertEquals(list, taskList.getTaskList());
    }

    @Test
    public void deleteTask_outOfIndex_exceptionThrown() {
        try {
            taskList.delete(-1);
            taskList.delete(5);
            fail();
        } catch (DukeOutOfBoundException e) {
            DukeOutOfBoundException exception = new DukeOutOfBoundException();
            assertEquals(exception.getMessage(), e.getMessage());
        }
    }

    @Test
    public void markTask_outOfIndex_exceptionThrown() {
        try {
            taskList.mark(-1);
            taskList.mark(5);
            fail();
        } catch (DukeOutOfBoundException e) {
            DukeOutOfBoundException exception = new DukeOutOfBoundException();
            assertEquals(exception.getMessage(), e.getMessage());
        }
    }

    @Test
    public void unmarkTask_outOfIndex_exceptionThrown() {
        try {
            taskList.unmark(-1);
            taskList.unmark(5);
            fail();
        } catch (DukeOutOfBoundException e) {
            DukeOutOfBoundException exception = new DukeOutOfBoundException();
            assertEquals(exception.getMessage(), e.getMessage());
        }
    }
}
