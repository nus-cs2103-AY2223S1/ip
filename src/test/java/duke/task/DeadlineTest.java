package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Task task = new Deadline("assignment", LocalDate.parse("2022-03-14"));
        assertEquals("[D][ ] assignment (by: Mar 14 2022)", task.toString());
        task.markAsDone();
        assertEquals("[D][X] assignment (by: Mar 14 2022)", task.toString());
    }

    @Test
    public void testEqualsMethod() {
        Task a = new Deadline("write essay", LocalDate.parse("2020-11-01"));
        Task b = new Deadline("write essay", LocalDate.parse("2020-11-01"));
        Task c = new Deadline("watch tv", LocalDate.parse("2020-11-01"));
        Task d = new Deadline("write essay", LocalDate.parse("2021-10-01"));
        Task e = new Event("write essay", LocalDate.parse("2020-11-01"));
        assertEquals(a, a);
        assertEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(a, d);
        assertNotEquals(a, e);
    }
}
