package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.command.AddCommand;
import duke.ui.Ui;

/**
 * Tests for AddCommand class.
 *
 * @author Elgin
 */
public class AddCommandTest {
    /**
     * Creates a ToDo task successfully and adds to tasks.
     *
     * @result ToDo task created on execution, and is within the TaskList.
     */
    @Test
    public void executes_addToDo_success() {
        AddCommand addCommand = new AddCommand("todo", "sleep eat");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        addCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getTaskLen());

        ArrayList<Task> addedTasks = tasks.getTasks();
        Task task = addedTasks.get(0);

        assertTrue(task instanceof ToDo);
        assertEquals("sleep eat", task.getTaskName());
    }

    /**
     * Creates an Event task successfully and adds to tasks.
     *
     * @result Event task created on execution, and is within the TaskList.
     */
    @Test
    public void executes_addEvent_success() {
        AddCommand addCommand = new AddCommand("event", "eat /at 2020-12-12 1800");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        addCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getTaskLen());

        ArrayList<Task> addedTasks = tasks.getTasks();
        Task task = addedTasks.get(0);

        assertTrue(task instanceof Event);
        assertEquals("eat", task.getTaskName());
    }

    /**
     * Creates a Deadline task successfully and adds to tasks.
     *
     * @result Deadline task created on execution, and is within the TaskList.
     */
    @Test
    public void executes_addDeadline_success() {
        AddCommand addCommand = new AddCommand("deadline", "ip /by 2020-01-12 2356");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        addCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getTaskLen());

        ArrayList<Task> addedTasks = tasks.getTasks();
        Task task = addedTasks.get(0);

        assertTrue(task instanceof Deadline);
        assertEquals("ip", task.getTaskName());
    }

    /**
     * Fails to create a Deadline Task on invalid arguments (i.e. missing /by statement).
     *
     * @result Throws DukeException which gives the correct error message.
     */
    @Test
    public void executes_addInvalidDeadlineTask_dukeExceptionThrown() {
        // Missing /by
        AddCommand addCommand = new AddCommand("deadline", "eat 2020-12-12 2100");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        try {
            addCommand.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Usage description /by date time", e.getMessage());
        }
    }

    /**
     * Fails to create an Event Task on invalid arguments (i.e. missing /at).
     *
     * @result Throws DukeException, with the correct error message.
     */
    @Test
    public void executes_addInvalidEventTask_dukeExceptionThrown() {
        // Missing /at
        AddCommand addCommand = new AddCommand("event", "eat 2020-12-12 2359");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        try {
            addCommand.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Usage event description /at date time", e.getMessage());
        }
    }
}
