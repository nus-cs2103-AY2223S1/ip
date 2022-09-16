package blob.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import blob.exception.InvalidPriorityException;
import org.junit.jupiter.api.Test;

import blob.exception.InvalidDateFormatException;

public class EventTest {

    @Test
    public void newEvent_wrongDateFormat_exceptionThrown() {
        try {
            Task task = new Event("test task", "02/Oct/2000", "//h");
            fail();
        } catch (InvalidDateFormatException e) {
            assertArrayEquals(new String[] {
                "Blob does not understand input datetime...",
                "USAGE: Dates have to be in the following formats: \n"
                    + "\t<yyyy-MM-dd>, <dd-MM-yyyy>, <d MMM yyyy>, <MMM d yyyy> \n "
                    + "\tOptionally include time as <HH:mm> " },
                e.getBlobMessages());
        } catch (InvalidPriorityException e) {
            fail();
        }

    }

    @Test
    public void testToString() {
        try {
            Task task = new Event("test task", "02 Oct 2000", "//h");
            assertEquals("<HIGH>[E][ ] test task (at: 2 Oct 2000)", task.toString());

            task.markAsDone();
            assertEquals("[E][\u2713] test task (at: 2 Oct 2000)", task.toString());
        } catch (InvalidDateFormatException | InvalidPriorityException e) {
            fail();
        }
    }
}
