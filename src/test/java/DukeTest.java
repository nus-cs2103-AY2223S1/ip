import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * Class for tests to be run on the Duke bot.
 */
public class DukeTest {

    /**
     * A test for the creation of ToDo task in the Duke bot.
     */
    @Test
    public void testToDo() {
        ToDo newToDo = new ToDo("To Do Test");
        assertEquals(newToDo.output(), "[T][ ] To Do Test");
    }

    /**
     * A test for the creation of Deadline task in the Duke bot.
     */
    @Test
    public void testDeadline() {
        Deadline newDeadline = new Deadline("Deadline Test", "2044-04-04");
        assertEquals(newDeadline.output(), "[D][ ] Deadline Test (by: 04 April 2044)");
    }

    /**
     * A test for the creation of an Event task in the Duke bot.
     */
    @Test
    public void testEvent() {
        Event newEvent = new Event("Event Test", "Sunday 2pm");
        assertEquals(newEvent.output(), "[E][ ] Event Test (at: Sunday 2pm)");
    }

    /**
     * A test for the Mark instruction in the Duke bot.
     */
    @Test
    public void testMark() {
        ToDo markToDo = new ToDo("Mark Test");
        markToDo.mark();
        assertEquals(markToDo.output(), "[T][X] Mark Test");
    }

    /**
     * A test for the UnMark instruction in the Duke bot.
     */
    @Test
    public void testUnMark() {
        ToDo unMarkToDo = new ToDo("UnMark Test");
        unMarkToDo.mark();
        assertEquals(unMarkToDo.output(), "[T][X] UnMark Test");
        unMarkToDo.unMark();
        assertEquals(unMarkToDo.output(), "[T][ ] UnMark Test");
    }
}
