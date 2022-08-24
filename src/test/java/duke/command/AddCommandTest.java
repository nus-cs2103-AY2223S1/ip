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
import duke.ui.Ui;


/**
 * Tests for AddCommand class.
 *
 * @author Elgin
 */
public class AddCommandTest {
    @Test
    public void executes_addToDo_success() {
        AddCommand addCommand = new AddCommand("todo", "sleep eat");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/duke.txt");

        addCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getTaskLen());

        ArrayList<Task> addedTasks = tasks.getTasks();
        Task task = addedTasks.get(0);

        assertTrue(task instanceof ToDo);
        assertEquals("sleep eat", task.getTaskName());
    }

    @Test
    public void executes_addEvent_success() {
        AddCommand addCommand = new AddCommand("event", "eat /at 2020-12-12");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/duke.txt");

        addCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getTaskLen());

        ArrayList<Task> addedTasks = tasks.getTasks();
        Task task = addedTasks.get(0);

        assertTrue(task instanceof Event);
        assertEquals("eat", task.getTaskName());
    }

    @Test
    public void executes_addDeadline_success() {
        AddCommand addCommand = new AddCommand("deadline", "ip /by 2020-01-12");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/duke.txt");

        addCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getTaskLen());

        ArrayList<Task> addedTasks = tasks.getTasks();
        Task task = addedTasks.get(0);

        assertTrue(task instanceof Deadline);
        assertEquals("ip", task.getTaskName());
    }

    @Test
    public void executes_addInvalidDeadlineTask_dukeExceptionThrown() {
        // Missing /by
        AddCommand addCommand = new AddCommand("deadline", "eat 2020-12-12");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/duke.txt");

        try {
            addCommand.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Usage description /by deadline", e.getMessage());
        }
    }

    @Test
    public void executes_addInvalidEventTask_dukeExceptionThrown() {
        // Missing /at
        AddCommand addCommand = new AddCommand("event", "eat 2020-12-12");

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/duke.txt");

        try {
            addCommand.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Usage description /at time", e.getMessage());
        }
    }
}
