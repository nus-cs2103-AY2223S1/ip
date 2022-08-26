package duke.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    private static Todo todo;
    private static Deadline deadline;
    private static Event event;

    @BeforeAll
    public static void setUp() {
        todo = new Todo(" test");
        deadline = new Deadline(" test", LocalDate.parse("2022-03-21"));
        event = new Event(" test", " test");
    }

    @Test
    public void todoTest() {
        assertEquals(todo.toString(), "[T][ ] test");
    }

    @Test
    public void todoTest2() {
        assertTrue(todo instanceof Todo);
    }

    @Test
    public void deadlineTest() {
        assertEquals("[D][ ] test (by: 21 Mar 2022)", deadline.toString());
    }

    @Test
    public void deadlineTest2() {
        assertTrue(deadline instanceof Deadline);
    }

    @Test
    public void eventTest() {
        assertEquals("[E][ ] test (at: test)", event.toString());
    }

    @Test
    public void eventTest2() {
        assertTrue(event instanceof Event);
    }
}
