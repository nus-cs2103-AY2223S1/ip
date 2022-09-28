package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.types.Deadline;

public class DeadlineTest {
    private String invalidDate = "40/40/999";
    private String validDate = "01/01/2022";
    private String validDescription = "test description";
    /**
     * Test Deadline with valid input
     */
    @Test
    public void test1() {
        try {
            Deadline test = new Deadline(validDescription, true, validDate);
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    /**
     * Test Deadline with invalid input
     */
    @Test
    public void test2() {
        try {
            Deadline test = new Deadline(validDescription, false, invalidDate);
        } catch (DukeException e) {
            assertEquals("wrong format!", e.getMessage());
        }

    }
}
