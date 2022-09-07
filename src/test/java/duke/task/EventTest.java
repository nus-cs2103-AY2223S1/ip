package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        Task task = new Event("project meeting", LocalDate.parse("2022-11-02"));
        assertEquals("[E][ ] project meeting (at: Nov 02 2022)", task.toString());
        task.markAsDone();
        assertEquals("[E][X] project meeting (at: Nov 02 2022)", task.toString());
    }

    @Test
    public void testEqualsMethod() {
        Task a = new Event("write essay", LocalDate.parse("2020-11-01"));
        Task b = new Event("write essay", LocalDate.parse("2020-11-01"));
        Task c = new Event("watch tv", LocalDate.parse("2020-11-01"));
        Task d = new Event("write essay", LocalDate.parse("2021-10-01"));
        Task e = new Deadline("write essay", LocalDate.parse("2020-11-01"));
        assertEquals(a, a);
        assertEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(a, d);
        assertNotEquals(a, e);
    }
}
