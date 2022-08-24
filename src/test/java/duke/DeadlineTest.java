package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testToString() {
        try {
            assertEquals("[D][ ] return book (by: Jun 6 2022)", new Deadline("return book", "2022-06-06").toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void constructor_wrongFormat_exceptionThrown() {
        try {
            new Deadline("return book", "June 6th");
            fail();
        } catch (DukeException e) {
            assertEquals("Please provide a date in yyyy-mm-dd format.", e.getMessage());
        }
    }
}
