package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tests the Event class.
 */
public class EventTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse("12/12/2020 18:00", formatter);

    /**
     * Tests the toString method in the Event class.
     */
    @Test
    public void toStringTest() {
        Event event = new Event("concert", date);
        assertEquals("[E][ ]concert (at: Dec 12 2020 06:00 PM)" + "\n", event.toString());
    }
}
