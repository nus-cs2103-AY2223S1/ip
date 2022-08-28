package caca.tasks;

import caca.exceptions.InvalidDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for Event.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class EventTest {

    /**
     * Tests the behaviour of adding an event with a valid date and time.
     *
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    @Test
    public void eventDate_validDate_success() throws InvalidDateException {
        Event event = new Event("session 1", "01/10/2022 1600");
        assertEquals("[E][ ] session 1 (at: Oct 01 2022 16:00)",
                event.toString());
    }

    /**
     * Tests the behaviour of adding an event with an invalid day as date.
     */
    @Test
    public void eventDate_invalidDay_exceptionThrown() {
        try {
            Event event = new Event("session 2", "00/10/2022 1600");
            assertEquals("[E][ ] session 2 (at: Oct 00 2022 16:00)", event.toString());
            fail(); // Test should not reach this line.
        } catch (InvalidDateException e) {
            assertEquals("You have keyed in an invalid date and time!\n"
                            + "Please specify date and time in the format: dd/MM/yyyy HHmm\n"
                            + "E.g. 24/08/2022 2359",
                    e.getMessage());
        }
    }

    /**
     * Tests the behaviour of adding an event with an invalid time as date.
     */
    @Test
    public void eventDate_invalidTime_exceptionThrown() {
        try {
            Event event = new Event("session 3", "01/10/2022 2401");
            assertEquals("[E][ ] session 3 (at: Oct 01 2022 00:01)", event.toString());
            fail(); // Test should not reach this line.
        } catch (InvalidDateException e) {
            assertEquals("You have keyed in an invalid date and time!\n"
                            + "Please specify date and time in the format: dd/MM/yyyy HHmm\n"
                            + "E.g. 24/08/2022 2359",
                    e.getMessage());
        }
    }

    /**
     * Tests the behaviour of converting an event that is marked as done into file format.
     *
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    @Test
    public void toFileFormat_eventDone_success() throws InvalidDateException {
        Event event = new Event("session 4", "01/10/2022 1600", true);
        assertEquals("E | X | session 4 | 01/10/2022 1600",
               event.toFileFormat());
    }

    /**
     * Tests the behaviour of converting an event that is not marked as done into file format.
     *
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    @Test
    public void toFileFormat_eventUndone_success() throws InvalidDateException {
        Event event = new Event("session 5", "01/10/2022 1600", false);
        assertEquals("E |   | session 5 | 01/10/2022 1600",
                event.toFileFormat());
    }
}
