package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


public class EventTest {
    private String invalidDateTime = "01/01/2022 100:00";
    private String validDateTime = "01/01/2022 11:12";
    private String validDescription = "test description";
    /**
     * Test Events with valid input
     */
    @Test
    public void test1() {
        try {
            Event test = new Event(validDescription, true, validDateTime);
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    /**
     * Test Events with invalid input
     */
    @Test
    public void test2() {
        try {
            Deadline test = new Deadline(validDescription, false, invalidDateTime);
        } catch (DukeException e) {
            assertEquals("wrong format!", e.getMessage());
        }
    }
}
