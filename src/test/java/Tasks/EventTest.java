package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void addEventTest() {
        Event e = new Event("project meeting", LocalDate.parse("14 Jan 2022",
                DateTimeFormatter.ofPattern("dd MMM uuuu")));
        assertEquals("[E][ ] project meeting (at: 2022-01-14)", e.toString());
    }
}
