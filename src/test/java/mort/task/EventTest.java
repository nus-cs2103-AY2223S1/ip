package mort.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private final LocalDate date = LocalDate.parse("28/9/2022", DateTimeFormatter.ofPattern("d/M/yyyy"));
    private final LocalDateTime dateTime1 = LocalDateTime.parse("28/9/2022 1400",
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private final LocalDateTime dateTime2 = LocalDateTime.parse("1/10/2022 1000",
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private final Event event1 = new Event("CS2103T meeting", dateTime1, false);
    private final Event event2 = new Event("Tom's birthday", date, true);
    private final Event event3 = new Event("CS2106 midterm", dateTime2, false);

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", event1.getStatusIcon());
        assertEquals("X", event2.getStatusIcon());
    }

    @Test
    public void testGetSaveFormat() {
        assertEquals("E | 0 | CS2103T meeting | 28/9/2022 1400", event1.getSaveFormat());
        assertEquals("E | 1 | Tom's birthday | 28/9/2022", event2.getSaveFormat());
        assertEquals("E | 0 | CS2106 midterm | 1/10/2022 1000", event3.getSaveFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[E][ ] CS2103T meeting (at: 28 Sep 2022, 2:00 PM)", event1.toString());
        assertEquals("[E][X] Tom's birthday (at: 28 Sep 2022)", event2.toString());
        assertEquals("[E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)", event3.toString());
    }

    @Test
    public void testMark() {
        String expected1 = "You can't finish the same task twice, genius.\n"
                + "  [E][X] Tom's birthday (at: 28 Sep 2022)\n";
        String expected2 = "You really took your time with this one, didn't you?\n"
                + "  [E][X] CS2103T meeting (at: 28 Sep 2022, 2:00 PM)\n";

        assertEquals(expected1, event2.mark());
        assertEquals(expected2, event1.mark());
    }

    @Test
    public void testUnmark() {
        String expected1 = "You're trying to unmark a task you haven't done.\n"
                + "Let that sink in for a moment.\n  [E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)\n";
        String expected2 = "And here I was thinking you were getting somewhere...\n"
                + "  [E][ ] Tom's birthday (at: 28 Sep 2022)\n";

        assertEquals(expected1, event3.unmark());
        assertEquals(expected2, event2.unmark());
    }

    @Test
    public void testIsMatch() {
        assertTrue(event1.isMatch("[ ]"));
        assertTrue(event1.isMatch("E]"));
        assertTrue(event1.isMatch("2:"));
        assertFalse(event1.isMatch("meei"));
        assertTrue(event2.isMatch("[X"));
        assertTrue(event2.isMatch("tOm'S"));
        assertTrue(event2.isMatch("at"));
        assertFalse(event2.isMatch("[ ]"));
        assertFalse(event3.isMatch("french"));
    }

    @Test
    public void testIsDateMatch() {
        assertTrue(event1.isDateMatch(date));
        assertTrue(event2.isDateMatch(date));
        assertFalse(event3.isDateMatch(date));
    }
}
