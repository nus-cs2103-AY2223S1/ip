package duke.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class UiTest {
    private Ui ui = new Ui();
    private ArrayList<Task> tasks = new ArrayList<>();
    private Todo todo1;
    private Todo todo2;

    {
        try {
            todo1 = new Todo("first todo.");
            todo2 = new Todo("second todo.");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

        tasks.add(todo1);
        tasks.add(todo2);
    }

    private TaskList taskList = new TaskList(tasks);

    @Test
    public void getStringDukeOpening_stringRepresentation_correct() {
        assertEquals("Hello! I'm Duke! What can I do for you?", ui.getStringDukeOpening());
    }

    @Test
    public void getStringDukeClosing_stringRepresentation_correct() {
        assertEquals("Bye. Hope to see you again soon!", ui.getStringDukeClosing());
    }

    @Test
    public void getStringDukeException_stringRepresentation_correct() {
        DukeException de = new DukeException("This is a test Duke Exception!");
        assertEquals("OOPS! This is a test Duke Exception!", ui.getStringDukeException(de));
    }

    @Test
    public void getStringIoException_stringRepresentation_correct() {
        IOException de = new IOException("This is a test IO Exception!");
        assertEquals("OOPS! This is a test IO Exception!", ui.getStringIoException(de));
    }

    @Test
    public void getStringDateTimeParseException_stringRepresentation_correct() {
        assertEquals(
                "All dates must be in the format (yyyy-MM-dd HH:mm)!",
                ui.getStringDateTimeParseException()
        );
    }

    @Test
    public void getStringUnexpectedException_stringRepresentation_correct() {
        assertEquals(
                "An unexpected exception has occurred.",
                ui.getStringUnexpectedException()
        );
    }

    @Test
    public void getStringTasks_stringRepresentation_correct() {
        assertEquals(
                "Here are the tasks in your list:\n1.[T][ ] first todo.\n2.[T][ ] second todo.\n",
                ui.getStringTasks(taskList)
        );
    }

    @Test
    public void getStringMatchingTasks_stringRepresentation_correct() {
        assertEquals(
                "Here are the matching tasks in your list:\n1.[T][ ] first todo.\n2.[T][ ] second todo.\n",
                ui.getStringMatchingTasks(tasks)
        );
    }

    @Test
    public void getStringAddTask_stringRepresentation_correct() {
        assertEquals(
                "Got it. I've added this task:\n  [T][ ] second todo.\nNow you have 2 tasks in the list.\n",
                ui.getStringAddTask(todo2, taskList)
        );
    }

    @Test
    public void getStringDeleteTask_stringRepresentation_correct() {
        assertEquals(
                "Noted. I've removed this task:\n  [T][ ] second todo.\nNow you have 2 tasks in the list.\n",
                ui.getStringDeleteTask(todo2, taskList)
        );
    }

    @Test
    public void getStringChangeTaskStatus_trueStringRepresentation_correct() {
        assertEquals(
                "Nice! I've marked this task as done:\n  [T][ ] second todo.\n",
                ui.getStringChangeTaskStatus(todo2, true)
        );
    }

    @Test
    public void getStringChangeTaskStatus_falseStringRepresentation_correct() {
        assertEquals(
                "OK, I've marked this task as not done yet:\n  [T][ ] second todo.\n",
                ui.getStringChangeTaskStatus(todo2, false)
        );
    }

    @Test
    public void getReminders_deadlinesStringRepresentation_correct() {
        assertEquals(
                "Here are the deadlines you want reminders for:\n",
                ui.getReminders(taskList, "deadline")
        );
    }

    @Test
    public void getReminders_eventsStringRepresentation_correct() {
        assertEquals(
                "Here are the events you want reminders for:\n",
                ui.getReminders(taskList, "event")
        );
    }
}
