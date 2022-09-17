package mort.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final LocalDate date = LocalDate.parse("16/9/2022", DateTimeFormatter.ofPattern("d/M/yyyy"));
    private final LocalDateTime dateTime1 = LocalDateTime.parse("16/9/2022 1200",
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private final LocalDateTime dateTime2 = LocalDateTime.parse("18/9/2022 2359",
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private final Deadline deadline1 = new Deadline("spanish video project", dateTime1, false);
    private final Deadline deadline2 = new Deadline("CS2103T IP submission", date, true);
    private final Deadline deadline3 = new Deadline("CS2105 Assignment 1", dateTime2, false);

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", deadline1.getStatusIcon());
        assertEquals("X", deadline2.getStatusIcon());
    }

    @Test
    public void testGetSaveFormat() {
        assertEquals("D | 0 | spanish video project | 16/9/2022 1200", deadline1.getSaveFormat());
        assertEquals("D | 1 | CS2103T IP submission | 16/9/2022", deadline2.getSaveFormat());
        assertEquals("D | 0 | CS2105 Assignment 1 | 18/9/2022 2359", deadline3.getSaveFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[D][ ] spanish video project (by: 16 Sep 2022, 12:00 PM)", deadline1.toString());
        assertEquals("[D][X] CS2103T IP submission (by: 16 Sep 2022)", deadline2.toString());
        assertEquals("[D][ ] CS2105 Assignment 1 (by: 18 Sep 2022, 11:59 PM)", deadline3.toString());
    }

    @Test
    public void testMark() {
        String expected1 = "You can't finish the same task twice, genius.\n"
                + "  [D][X] CS2103T IP submission (by: 16 Sep 2022)\n";
        String expected2 = "You really took your time with this one, didn't you?\n"
                + "  [D][X] spanish video project (by: 16 Sep 2022, 12:00 PM)\n";

        assertEquals(expected1, deadline2.mark());
        assertEquals(expected2, deadline1.mark());
    }

    @Test
    public void testUnmark() {
        String expected1 = "You're trying to unmark a task you haven't done.\n"
                + "Let that sink in for a moment.\n  [D][ ] CS2105 Assignment 1 (by: 18 Sep 2022, 11:59 PM)\n";
        String expected2 = "And here I was thinking you were getting somewhere...\n"
                + "  [D][ ] CS2103T IP submission (by: 16 Sep 2022)\n";

        assertEquals(expected1, deadline3.unmark());
        assertEquals(expected2, deadline2.unmark());
    }

    @Test
    public void testIsMatch() {
        assertTrue(deadline1.isMatch("[ ]"));
        assertTrue(deadline1.isMatch("D]"));
        assertTrue(deadline1.isMatch("12"));
        assertFalse(deadline1.isMatch("spa i"));
        assertTrue(deadline2.isMatch("[X"));
        assertTrue(deadline2.isMatch("cS2103t"));
        assertTrue(deadline2.isMatch("by"));
        assertFalse(deadline2.isMatch("[ ]"));
        assertFalse(deadline3.isMatch("french"));
    }

    @Test
    public void testIsDateMatch() {
        assertTrue(deadline1.isDateMatch(date));
        assertTrue(deadline2.isDateMatch(date));
        assertFalse(deadline3.isDateMatch(date));
    }
}
