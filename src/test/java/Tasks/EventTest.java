package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Event test class to test event object methods
 */
public class EventTest {

    /**
     * Test for string representation of an event task
     */
    @Test
    public void addEventTest() {
        Event event = new Event("project meeting", LocalDateTime.parse("14-Jan-2022 12:00",
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")));
        assertEquals("[E][ ] project meeting (at: 2022/01/14 12.00PM)", event.toString());
    }

    /**
     * Test for marking an event task as done
     */
    @Test
    public void addEventTestMarked() {
        Event event = new Event("Tester2", LocalDateTime.parse("10-Jan-2020 12:30",
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")));
        event.setMarked();
        assertEquals("[E][X] Tester2 (at: 2020/01/10 12.30PM)", event.toString());
    }
}
