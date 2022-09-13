package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toStringTest() {
        assertEquals("[E][ ]  meeting (at: 10am)", new Event(" meeting", "10am").toString());
    }

    @Test
    public void saveFileStringTest() {
        assertEquals("E | 0 |  meeting | 10am", new Event(" meeting", "10am").saveString());
    }

}
