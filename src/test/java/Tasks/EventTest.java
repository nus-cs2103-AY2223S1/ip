package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void addEventTest() {
        Event event = new Event("project meeting", LocalDateTime.parse("14-Jan-2022 12:00",
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")));
        assertEquals("[E][ ] project meeting (at: 2022/01/14 12.00PM)", event.toString());
    }
}
