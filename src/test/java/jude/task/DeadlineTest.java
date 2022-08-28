package jude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test template adapted from https://se-education.org/guides/tutorials/junit.html
import org.junit.jupiter.api.Test;

/**
 * Tests functionality of Deadline class.
 */
public class DeadlineTest {

    /**
     * Test Case 1 of toString method.
     * Tests for case where deadline task is complete.
     */
    @Test
    public void testToString1() {
        Deadline deadline = new Deadline("Deadline Task 1", true, "22 Aug 2022 00:00");
        assertEquals("[D][X] Deadline Task 1 (by: 22 Aug 2022 00:00)", deadline.toString());
    }

    /**
     * Test Case 2 of toString method.
     * Tests for case where deadline task is incomplete.
     */
    @Test
    public void testToString2() {
        Deadline deadline = new Deadline("Deadline Task 2", false, "23 Aug 2022 21:00");
        assertEquals("[D][ ] Deadline Task 2 (by: 23 Aug 2022 21:00)", deadline.toString());
    }

    /**
     * Test Case 3 of toString method.
     * Tests for case where {@code Deadline} task description is null, to ensure that the program
     * does not crash.
     */
    @Test
    public void testToString3() {
        Deadline deadline = new Deadline(null, false, "23 Aug 2022 21:00");
    }

    /**
     * Test Case 1 of testToFileString1.
     * Tests for case where deadline task is complete.
     */
    @Test
    public void testToFileSaveString1() {
        Deadline deadline = new Deadline("Deadline Task 4", true, "24 Aug 2022 12:00");
        assertEquals("D\n"
                + "Deadline Task 4\n"
                + "1\n"
                + "24 Aug 2022 12:00\n", deadline.toFileSaveString());
    }

    /**
     * Test Case 2 of testToFileString1.
     * Tests for case where deadline task is incomplete.
     */
    @Test
    public void testToFileSaveString2() {
        Deadline deadline = new Deadline("Deadline Task 5", false, "8 Dec 2022 15:00");
        assertEquals("D\n"
                + "Deadline Task 5\n"
                + "0\n"
                + "8 Dec 2022 15:00\n", deadline.toFileSaveString());
    }
}
