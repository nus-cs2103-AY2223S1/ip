package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class for testing the Deadline object.
 */
public class DeadlineTest {

    /**
     * Tests if the toString() method of the Deadline class works as expected.
     */
    @Test
    public void descriptionTest() {
        String description = "homework";
        String by = "1/3/2019 1500";
        Deadline test = new Deadline(description, by);
        assertEquals("[D][ ]homework (by: Mar 01 2019 15:00)", test.toString());
    }
}
