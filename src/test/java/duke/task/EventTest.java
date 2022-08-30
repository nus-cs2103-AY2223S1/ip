package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getStringToSaveTest() {
        assertEquals("E | 0 | meeting | 2022-08-06",
                new Event("meeting", "2022-08-06").getStringToSave());
    }

    @Test
    public void toStringTest() {
        assertEquals("[E][ ] meeting (at: Aug 6 2022)",
                new Event("meeting", "2022-08-06").toString());
    }
}
