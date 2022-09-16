package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

class TaskListTest {

    private static final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent =
            new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void resetStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void addTask() throws DukeException {
        TaskList curr = TaskList.getInstance();
        String[] input = {"todo", "cycle"};
        String expected = "added [T][ ] cycle\n"
                + "Now you have 1 tasks in the list\n";
        curr.addTask(input);
        assertEquals(expected, outContent.toString());

        outContent.reset();
        input = new String[]{"event",
            "project meeting /at 2022-08-22 1800"
        };
        expected = "added [E][ ] project meeting (at: Aug 22 2022 18:00)\n"
                + "Now you have 2 tasks in the list\n";
        curr.addTask(input);
        assertEquals(expected, outContent.toString());
        outContent.reset();

        input = new String[]{"deadline",
            "math submission /by 2022-08-23 2359"
        };
        expected = "added [D][ ] math submission (by: Aug 23 2022 23:59)\n"
                + "Now you have 3 tasks in the list\n";
        curr.addTask(input);
        assertEquals(expected, outContent.toString());
        outContent.reset();
    }

    @Test
    void delete() throws DukeException {
        TaskList curr = TaskList.getInstance();
        String[] input = {"todo", "cycle"};
        curr.addTask(input);
        String expected = "added [T][ ] cycle\n"
                + "Now you have 1 tasks in the list\n";
        assertEquals(expected, outContent.toString());
        outContent.reset();
        expected = "Removed the following task:\n"
                + "[T][ ] cycle";
        curr.delete("1");
        assertEquals(expected, outContent.toString().trim());
        outContent.reset();

    }

    @Test
    void getTaskList() {
        TaskList curr = TaskList.getInstance();
        DukeException e = assertThrows(DukeException.class, () -> curr.delete("0"));
        assertEquals("Invalid selection for deletion", e.getMessage());

    }
}
