package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/** A class for testing the Deadline object. */
public class DeadlineTest {

    /**
     * Tests if the toString() method of the Deadline class works as expected.
     */
    @Test
    public void descriptionTest() {
        String description = "homework";
        String by = "1/3/2019 1500";
        Deadline testDeadline = new Deadline(description);
        testDeadline.setDate("2019-03-01");
        testDeadline.setTime("15:00");
        assertEquals("[D][ ][ ]homework (by: Mar 01 2019 15:00)", testDeadline.toString());
    }
}
