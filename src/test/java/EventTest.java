package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class that tests the Event class.
 */
public class EventTest {

    /**
     * Tests if the toString() method of the Event class works as expected.
     */
    @Test
    public void descriptionTest() {
        String description = "soccer match";
        String at = "2/8/2020 0800";
        Event test = new Event(description, at);
        test.parseDate("2020-08-02");
        test.parseTime("08:00");
        assertEquals("[E][ ]soccer match (at: Aug 02 2020 08:00)", test.toString());
    }
}
