package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    private String invalidDateTime = "01/01/2022 100:00";
    private String validDateTime = "01/01/2022 11:12";
    private String validDescription = "test description";
    /**
     * Test Events with valid input
     */
    @Test
    public void Test1() {
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
    public void Test2() {
        try {
            Deadline test = new Deadline(validDescription, false, invalidDateTime);
        } catch (DukeException e) {
            assertEquals("wrong format!", e.getMessage());
        }
    }
}
