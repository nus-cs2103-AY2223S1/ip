package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getType_EReturned() {
        Event test = new Event("test", "Jan 1st, 2000");
        assertEquals("E", test.getType());
    }
}
