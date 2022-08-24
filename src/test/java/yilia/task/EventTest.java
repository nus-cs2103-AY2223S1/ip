package yilia.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {
    private final Event event1 = new Event("go to the park", "2019-03-12");
    private final Event event2 = new Event("go to the concert", true, "2020-01-02");

    @Test
    public void toString_normalInput_writtenCorrectly() {
        assertEquals(event1.toString(), "[E][ ] go to the park (at: Mar 12 2019)");
        assertEquals(event2.toString(), "[E][X] go to the concert (at: Jan 2 2020)");
    }

    @Test
    public void status_check_statusCorrect() {
        event1.check();
        event2.check();
        assertTrue(event1.status());
        assertTrue(event2.status());
    }

    @Test
    public void status_uncheck_statusCorrect() {
        event1.uncheck();
        event2.uncheck();
        assertFalse(event1.status());
        assertFalse(event2.status());
    }

    @Test
    public void parse_normalInput_writtenCorrectly() {
        assertEquals(event1.parse(), "E / 0 / go to the park / 2019-03-12");
        assertEquals(event2.parse(), "E / 1 / go to the concert / 2020-01-02");
    }
}
