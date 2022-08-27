package ava;

import ava.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void markTest() {
        Todo todo = new Todo("Eat lunch", false);
        assertEquals(todo.markDone().toString(), "[T][X] Eat lunch");
    }

    @Test
    public void formatFileTest() {
        Todo todo = new Todo("Read Pre-Lecture Notes", false);
        assertEquals(todo.formatChange(), "T | 0 | Read Pre-Lecture Notes");
    }
}
