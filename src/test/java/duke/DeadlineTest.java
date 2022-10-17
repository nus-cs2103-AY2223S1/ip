
package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.dukeexception.DateTimeFormatException;
import duke.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testToString() throws DateTimeFormatException {
        Deadline deadline = new Deadline("Job Application", "2022-06-29");
        deadline.changeStatus();
        assertEquals("     1.[D][X] Job Application (by: Jun 29 2022)", deadline.toString());
    }

}
