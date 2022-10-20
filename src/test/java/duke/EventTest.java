package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class EventTest {
    @Test
    public void test() {
        Event event = new Event("hi", LocalDate.parse("2022-02-02"));
        assertEquals(event.isDone, false);
        assertEquals(event.at, LocalDate.parse("2022-02-02"));

        event.mark();
        assertEquals(event.isDone, true);
    }
}
