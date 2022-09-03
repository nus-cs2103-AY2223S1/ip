package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DukeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void ui_printTaskList_oneTaskPrinted() {
        ArrayList<Task> ts = new ArrayList<>();
        ts.add(new Todo("return book"));

        TaskList tasks = new TaskList(ts);
        Ui ui = new Ui();

        ui.printTaskList(tasks);
        String expected = "Here are the tasks in your list:\r\n"
                + "1.[T][ ] return book\r\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void ui_printNoOfTasks_twoPrinted() {
        ArrayList<Task> ts = new ArrayList<>();
        ts.add(new Todo("return book"));
        ts.add(new Deadline("blah", "tonight"));

        TaskList tasks = new TaskList(ts);
        Ui ui = new Ui();

        ui.printNoOfTasks(tasks);
        String expected = "Now you have 2 tasks in your list.\r\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void ui_printErrorMessage_invalidCommandPrinted() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        ui.printErrorMessage(new InvalidCommandException("hello"), tasks);
        String expected = ":( Sorry I don't understand the command: \"hello\"\r\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void taskList_getTasksLength_threeReturned() {
        ArrayList<Task> ts = new ArrayList<>();
        ts.add(new Todo("return book"));
        ts.add(new Deadline("blah", "tonight"));
        ts.add(new Event("blah", "tonight"));
        TaskList tasks = new TaskList(ts);

        assertEquals(3, tasks.getTasksLength());
    }

    @Test
    public void taskList_addTodo_emptyTodoExceptionThrown() {
        TaskList tasks = new TaskList();

        assertThrows(EmptyTodoException.class, () -> {
            tasks.addTodo("todo");
        });

    }

    @Test
    public void taskList_addDeadline_deadlineFormatExceptionThrown() {
        TaskList tasks = new TaskList();

        assertThrows(DeadlineFormatException.class, () -> {
            tasks.addDeadline("deadline blah by tonight");
        });

    }

    @Test
    public void taskList_addEvent_eventFormatExceptionThrown() {
        TaskList tasks = new TaskList();

        assertThrows(EventFormatException.class, () -> {
            tasks.addEvent("event blah tonight");
        });

    }
}
