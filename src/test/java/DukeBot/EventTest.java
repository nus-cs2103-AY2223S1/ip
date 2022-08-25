package DukeBot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test Event Class.
 */
public class EventTest {

    /**
     * Test to test the getTaskType method of Event.
     *
     * @throws DukeException
     */
    @Test
    public void taskTypeTest() throws DukeException {
        assertEquals("E", new Event("x", "2000-10-10").getTaskType());
    }

    /**
     * Test to test the toString method of Event.
     *
     * @throws DukeException
     */
    @Test
    public void stringConversionTest() throws DukeException {
        assertEquals("[E][ ] go to fair (at: Nov 28 2000)", new Event("go to fair", "2000-11-28").toString());
    }
}
