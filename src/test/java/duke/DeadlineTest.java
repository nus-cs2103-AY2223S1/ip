package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    /**
     * Test Deadline with valid input
     */
    @Test
    void toString_normalInput_writtenCorrectly() {
        try {
            assertEquals("[D][ ] homework (by: Aug 25 2022)",
                    new Deadline("homework", "2022-08-25").toString());
        } catch (DukeException e) {
            fail("Exception thrown for correct input", e);
        }
    }

    /**
     * Test Deadline with invalid input
     */
    @Test
    void constructor_wrongDateFormatInput_exceptionThrown() {
        try {
            Deadline d = new Deadline("homework", "25-08-2022");
        } catch (DukeException e) {
            assertEquals("Please enter valid date format (yyyy-mm-dd)", e.getMessage());
        }
    }
}