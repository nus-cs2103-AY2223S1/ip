package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class deadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("testing");
        assertEquals("[D][ ] testing (by: null)", deadline.toString());
    }

    @Test
    public void testSetDate() {
        Deadline deadline = new Deadline("testing");
        deadline.setDate(new FormatDate("2022-08-26"));
        assertEquals("Aug 26 2022", deadline.date.toString());
    }
}
