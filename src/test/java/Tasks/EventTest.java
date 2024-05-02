package Tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTest {
    @Test
    public void addEventTest() {
        Event e = new Event("test 1", LocalDate.parse("14 Jan 2022",
                                                            DateTimeFormatter.ofPattern("dd MMM uuuu")));
        assertEquals("[E][ ] test 1 (at: 2022-01-14)", e.toString());
    }
}
