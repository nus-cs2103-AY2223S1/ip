import duke.tasks.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class for tests to be run on the Duke bot.
 */
public class DukeTest {

    /**
     * A test for the creation of to do task in the Duke bot.
     */
    @Test
    public void test_ToDo() {
        Todo newToDo = new Todo("To Do Test");
        assertEquals(newToDo.output(), "[T][ ] To Do Test");
    }

    /**
     * A test for the creation of deadline task in the Duke bot.
     */
    @Test
    public void test_Deadline() {
        Deadline newDeadline = new Deadline("Deadline Test", "2044-04-04");
        assertEquals(newDeadline.output(), "[D][ ] Deadline Test (by: 04 April 2044)");
    }

}
