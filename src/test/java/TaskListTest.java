import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.Todo;

/**
 * To unit test the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests the creation of a todo object.
     *
     * @throws DukeException not thrown.
     */
    @Test
    public void testCreateTodo() throws DukeException {
        TaskList test = new TaskList();
        test.createTodo("todo todotest");
        Task[] testArray = test.taskListToArray();
        assertEquals(new Todo("todotest").toString(), testArray[0].toString());
    }

    /**
     * Tests the creation of a deadline object.
     *
     * @throws DukeException not thrown.
     */
    @Test
    public void testCreateDeadline() throws DukeException {
        TaskList test = new TaskList();
        test.createDeadline("deadline deadlinetest /by 2012-12-12 12:12");
        Task[] testArray = test.taskListToArray();
        assertEquals(new Deadline("deadlinetest", "2012-12-12 12:12").toString(), testArray[0].toString());
    }

    /**
     * Tests the creation of a event object.
     *
     * @throws DukeException not thrown.
     */
    @Test
    public void testCreateEvent() throws DukeException {
        TaskList test = new TaskList();
        test.createEvent("event eventtest /at tmr");
        Task[] testArray = test.taskListToArray();
        assertEquals(new Event("eventtest", "tmr").toString(), testArray[0].toString());
    }
}
