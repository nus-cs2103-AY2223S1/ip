package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class EventTest {
    @Test
    public void eventChangeFormatTest_Success() {
        Event e1 = new Event("This is a Test Event", LocalDateTime.of(2022, 9, 4, 12, 00));
        Event e2 = new Event("This is also a Test Event", LocalDateTime.of(2022, 4, 9, 16, 00));
        e1.mark();
        assertEquals("E | X | This is a Test Event | 2022-09-04 1200", e1.changeFormat());
        assertEquals("E |   | This is also a Test Event | 2022-04-09 1600", e2.changeFormat());
    }
}
