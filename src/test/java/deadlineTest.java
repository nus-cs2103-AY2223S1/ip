package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class deadlineTest {
    @Test
    public void testToString() {
        deadline deadline = new deadline("testing");
        assertEquals("[D][ ] testing (by: null)", deadline.toString());
    }

    @Test
    public void testSetDay() {
        deadline deadline = new deadline("testing");
        deadline.setDate(new formatDate("2022-08-26"));
        assertEquals("Aug 26 2022", deadline.date.toString());
    }
}
