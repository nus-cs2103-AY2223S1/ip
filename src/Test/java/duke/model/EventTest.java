package duke.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void toStorage_success() {
        Event event = new Event("try jumping", "2008-04-03", "2009-10-23");
        assertEquals(event.toStorage(), "E | 0 | try jumping | 2008-04-03 | 2009-10-23\n");
    }
}
