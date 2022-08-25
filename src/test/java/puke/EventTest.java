package puke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void addEventTest() {
        Task e = new Event("Eric Chou concert", LocalDate.parse("2030-12-12"));
        assertEquals("[E][ ] Eric Chou concert(at: Dec 12 2030)", e.toString());
    }
}
