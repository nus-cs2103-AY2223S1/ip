package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void encodeTest() {
        Event event = new Event("homework", LocalDate.parse("2022-01-02"));
        assertEquals("E" + Task.ENCODING_SEPARATOR + "2022-01-02"
                + Task.ENCODING_SEPARATOR + "homework"
                + Task.ENCODING_SEPARATOR + "false", event.encode());
        event = new Event("homework", LocalDate.parse("2022-01-02"), true);
        assertEquals("E" + Task.ENCODING_SEPARATOR + "2022-01-02"
                + Task.ENCODING_SEPARATOR + "homework"
                + Task.ENCODING_SEPARATOR + "true", event.encode());
    }

    @Test
    public void toStringTest() {
        Event event = new Event("homework", LocalDate.parse("2022-01-02"));
        assertEquals("[E][ ] homework (at: 2022-01-02)", event.toString());
        event = new Event("homework", LocalDate.parse("2022-01-02"), true);
        assertEquals("[E][X] homework (at: 2022-01-02)", event.toString());
    }
}
