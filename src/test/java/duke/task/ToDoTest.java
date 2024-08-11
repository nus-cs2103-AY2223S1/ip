package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] write essay", new ToDo("write essay").toString());
    }

    @Test
    public void testEqualsMethod() {
        Task a = new ToDo("write essay");
        Task b = new ToDo("write essay");
        Task c = new ToDo("watch tv");
        Task d = new Task("write essay");
        Task e = new Deadline("write essay", LocalDate.parse("2020-11-01"));
        assertEquals(a, a);
        assertEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(a, d);
        assertNotEquals(a, e);
    }
}
