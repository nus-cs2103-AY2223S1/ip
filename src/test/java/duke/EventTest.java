package duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.format.DateTimeFormatter;

public class EventTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse("12/12/2020 18:00", formatter);
    @Test
    public void toStringTest() {
        Event event = new Event("concert", date);
        assertEquals("[E][ ]concert (at: Dec 12 2020 06:00 PM)" + "\n", event.toString());
    }
}
