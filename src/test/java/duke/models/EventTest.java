package duke.models;

import duke.DateParser;
import org.junit.jupiter.api.Test;

public class EventTest {

    /**
     * Test the toString() method in {@code Event}
     */
    @Test
    public void testToString() {
        Event event = new Event("return book", DateParser.parseDate("2020-08-08"));
        assert event.toString().equals("[E][ ] return book (at: Aug 8 2020)");
    }

    /**
     * Test the postpone method in {@code Event}
     */
    @Test
    public void testPostpone() {
        Event event = new Event("return book", DateParser.parseDate("2020-08-08"));
        event.postponeTask();
        assert event.toString().equals("[E][ ] return book (at: Aug 9 2020)");
    }
}
