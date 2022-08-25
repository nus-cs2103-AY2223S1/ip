package duke.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toStorage_success() {
        Event event = new Event("try jumping", "2008-04-03", "2009-10-23");
        assertEquals(event.toStorage(), "E | 0 | try jumping | 2008-04-03 | 2009-10-23\n");
    }
}