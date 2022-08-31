package scruffles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event e = new Event("yeet", "2022-09-09 from 16:30 to 18:30");
        assertEquals("[E][ ] yeet (at: 9 SEPTEMBER 2022 16:30 to 18:30)", e.toString());
    }
}
