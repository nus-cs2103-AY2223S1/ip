package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getType_eReturned() {
        Event test = new Event("test", "Jan 1st, 2000");
        assertEquals("E", test.getType());
    }
}
