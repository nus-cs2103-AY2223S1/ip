package blob.tasks;

import blob.exception.InvalidDateFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



public class DeadlineTest {

    @Test
    public void newEvent_wrongDateFormat_exceptionThrown() {
        try {
            Task task = new Deadline("test task", "02/Oct/2000");
            fail();
        } catch (InvalidDateFormatException e) {
            assertArrayEquals(new String[] { "Blob does not understand input datetime...",
                            "USAGE: Dates have to be in the following formats: \n" +
                                    "\t<yyyy-MM-dd>, <dd-MM-yyyy>, <d MMM yyyy>, <MMM d yyyy> \n " +
                                    "\tOptionally include time as <HH:mm> " },
                    e.getBlobMessages());
        }

    }

    @Test
    public void testToString() {
        try {
            Task task = new Event("test task", "02 Oct 2000");
            assertEquals("[D][ ] test task (by: 2 Oct 2000)", task.toString());

            task.markAsDone();
            assertEquals("[D][✓] test task (by: 2 Oct 2000)", task.toString());
        } catch (InvalidDateFormatException e) {
            fail();
        }
    }
}
