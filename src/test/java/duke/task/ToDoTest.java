package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void addToDoTest() {
        ToDo todo = new ToDo("Return Book");
        assertEquals("[T][ ] Return Book", todo.toString());
    }

    @Test
    public void markToDoTest() {
        ToDo todo = new ToDo("Return Book");
        todo.markAsDone();
        assertEquals("[T][X] Return Book", todo.toString());
    }

    @Test
    public void UnmarkToDoTest() {
        ToDo todo = new ToDo("Return Book");
        todo.markAsDone();
        todo.markAsUndone();
        assertEquals("[T][ ] Return Book", todo.toString());
    }

    @Test
    public void saveToDoTest() {
        ToDo todo = new ToDo("Return Book");
        assertEquals("T | 0 | Return Book", todo.save());
    }

    @Test
    public void getTimeTest() {
        ToDo todo = new ToDo("Return Book");
        assertEquals(LocalDate.MIN, todo.getTime());
    }
}
