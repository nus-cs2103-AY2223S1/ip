package duke.task;

import duke.DateStub;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Deadline class.
 */
public class DeadlineTest {

    /**
     * Test Case 1 of toString() method.
     * Tests for a Deadline task with only 1 word as description.
     */
    @Test
    public void toString_oneWordDescription_stringFormattedCorrectly() {
        Deadline deadline = new Deadline("Deadline",
                new DateStub(LocalDate.of(2022, 02, 02)));
        assertEquals("[D][ ] Deadline (by: Feb 2 2022)", deadline.toString());
    }

    /**
     * Test Case 2 of toString() method.
     * Tests for a Deadline task with more than one word as description.
     */
    @Test
    public void toString_multipleWordDescription_stringFormattedCorrectly() {
        Deadline deadline = new Deadline("CS2103T Tutorial",
                new DateStub(LocalDate.of(2022, 03, 22)));
        assertEquals("[D][ ] CS2103T Tutorial (by: Mar 22 2022)", deadline.toString());
    }

    /**
     * Test Case 3 of toString() method.
     * Tests for a Deadline task that has been marked as done.
     */
    @Test
    public void toString_taskMarkedAsDone_stringFormattedCorrectly() {
        Deadline deadline = new Deadline("CS2100 Lab 01",
                new DateStub(LocalDate.of(2022, 10, 11)));
        deadline.markAsDone();
        assertEquals("[D][X] CS2100 Lab 01 (by: Oct 11 2022)", deadline.toString());
    }

    /**
     * Test Case 1 of saveData() method.
     * Tests if Deadline tasked that is unmarked has been saved.
     */
    @Test
    public void saveData_unMarkedTask_stringFormattedCorrectly() {
        Deadline deadline = new Deadline("CS2103T Tutorial",
                new DateStub(LocalDate.of(2022, 03, 22)));
        assertEquals("D | 0 | CS2103T Tutorial | Mar 22 2022", deadline.saveData());
    }

    /**
     * Test Case 2 of saveData() method.
     * Tests if Deadline tasked that is marked has been saved.
     */
    @Test
    public void saveData_markedTask_stringFormattedCorrectly() {
        Deadline deadline = new Deadline("CS2100 Lab 01",
                new DateStub(LocalDate.of(2022, 10, 11)));
        deadline.markAsDone();
        assertEquals("D | 1 | CS2100 Lab 01 | Oct 11 2022", deadline.saveData());
    }
}
