package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringTest() {
        assertEquals(new Event("book fest", "Sunday 2-4pm").toString(),
                "[E][ ] book fest (at: Sunday 2-4pm)");
    }
}
