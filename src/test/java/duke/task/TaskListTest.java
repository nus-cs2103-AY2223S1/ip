package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskListTest {
    public static final int INITIAL_TASK_COUNT = 2;
    private TaskList tasks;
    private Deadline deadline;
    private Event event;
    private Todo todo;

    @BeforeEach
    public void setUp() throws DukeException {
        tasks = new TaskList();
        deadline = new Deadline("test deadline", LocalDate.parse("2022-01-01"));
        event = new Event("test event", LocalDate.parse("2022-02-02"));
        event.markDone();
        todo = new Todo("test todo");
        tasks.addTask(deadline);
        tasks.addTask(event);
    }

    @Test
    public void taskCount_matchInitialCount_success() {
        assertEquals(INITIAL_TASK_COUNT, tasks.taskCount());
    }

    @Test
    public void addTask_addOneTodo_success() {
        tasks.addTask(todo);
        assertEquals(INITIAL_TASK_COUNT + 1, tasks.taskCount());
        Task todoFromTaskList = assertDoesNotThrow(() -> tasks.getTask(3));
        assertSame(todo, todoFromTaskList);
    }

    @Test
    public void getTask_validIndex_success() {
        Task firstTask = assertDoesNotThrow(() -> tasks.getTask(1));
        Task secondTask = assertDoesNotThrow(() -> tasks.getTask(2));
        assertSame(deadline, firstTask);
        assertSame(event, secondTask);
    }

    @Test
    public void getTask_emptyList_exceptionThrown() {
        assertThrows(DukeException.class, () -> new TaskList().getTask(1));
    }

    @Test
    public void getTask_indexLessThanOne_exceptionThrown() {
        assertThrows(DukeException.class, () -> tasks.getTask(0));
    }

    @Test
    public void getTask_indexMoreThanTaskCount() {
        assertThrows(DukeException.class, () -> tasks.getTask(INITIAL_TASK_COUNT + 1));
    }

    @Test
    public void markTask_markUndoneTask_success() {
        assertDoesNotThrow(() -> tasks.markTask(1));
        assertTrue(deadline.isDone());
    }

    @Test
    public void markTask_markAlreadyDoneTask_exceptionThrown() {
        assertThrows(DukeException.class, () -> tasks.markTask(2));
    }

    @Test
    public void unmarkTask_unmarkDoneTask_success() {
        assertDoesNotThrow(() -> tasks.unmarkTask(2));
        assertFalse(event.isDone());
    }

    @Test
    public void unmarkTask_unmarkNotDoneTask_exceptionThrown() {
        assertThrows(DukeException.class, () -> tasks.unmarkTask(1));
    }

    @Test
    public void deleteTask_indexWithinRange_success() {
        Task deletedTask = assertDoesNotThrow(() -> tasks.deleteTask(1));
        assertSame(deadline, deletedTask);
        assertEquals(INITIAL_TASK_COUNT - 1, tasks.taskCount());
    }

    @Test
    public void getTasksOn_existingDate_success() {
        ArrayList<Task> filteredTasks = tasks.getTasksOn(LocalDate.parse("2022-02-02"));
        assertEquals(filteredTasks.size(), 1);
        assertSame(filteredTasks.get(0), event);
    }

    @Test
    public void searchTasks_queryInTaskList_success() {
        ArrayList<Task> filteredTasks = tasks.searchTasks("deadline");
        assertEquals(filteredTasks.size(), 1);
        assertSame(filteredTasks.get(0), deadline);
    }
}
