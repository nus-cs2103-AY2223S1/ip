package Duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] anime comp(at: Sep 22 2018)", new Event("anime comp", LocalDate.parse("2018-09-22")).toString());
    }
}
