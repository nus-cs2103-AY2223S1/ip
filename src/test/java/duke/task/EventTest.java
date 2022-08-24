package duke.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event e = new Event("Get married", LocalDate.parse("2029-07-08"));
        assertEquals(e.toString(), "[E][ ] Get married (at: Jul 8 2029)");
    }
}
