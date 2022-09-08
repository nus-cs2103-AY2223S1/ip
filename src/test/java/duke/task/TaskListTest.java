package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private static ByteArrayOutputStream output;
    private static PrintStream oldStream;

    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        output = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(output);
        oldStream = System.out;
        System.setOut(stream);
    }

    @AfterEach
    public void reset() {
        System.out.flush();
        System.setOut(oldStream);
    }

    @Test
    public void testPrintEmptyList() {
        tasks.printList();
        assertEquals(
                '\u2619' + " your list is empty. START adding some tasks to do now!\n",
                output.toString());
    }

    @Test
    public void add_todoTask_success() {
        tasks.add(new ToDo("go jogging"));
        assertEquals(
                '\u2619' + " added:\n     [T][ ] go jogging\n"
                        + "  you now have 1 task in the list. type list to see it!\n",
                output.toString()
        );
    }
}
