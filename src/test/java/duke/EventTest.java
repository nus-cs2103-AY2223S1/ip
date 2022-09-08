package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringTest() {
        assertEquals("[E][ ] block event (at: Tue, 30 August 2022, 8:00PM)",
                new Event("block event ", "30/8/2022 2000").toString());
    }
    @Test
    public void toLineTest() {
        assertEquals("E*0*block event *30/8/2022 2000",
                new Event("block event ", "30/8/2022 2000").toLine());
    }
}
