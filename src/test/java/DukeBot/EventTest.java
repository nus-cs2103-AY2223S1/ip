package DukeBot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void taskTypeTest() throws DukeException {
        assertEquals("E", new Event("x", "2000-10-10").getTaskType());
    }

    @Test
    public void stringConversionTest() throws DukeException {
        assertEquals("[E][ ] go to fair (at: Nov 28 2000)", new Event("go to fair", "2000-11-28").toString());
    }
}
