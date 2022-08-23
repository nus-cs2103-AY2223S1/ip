package task;

import jduke.task.Todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo todo = new Todo("Todo");
        assertEquals("[T][ ] Todo", todo.toString());
        assertEquals("T | 0 | Todo", todo.toStorageFormat());
    }

    @Test
    public void markTodoTest() {
        Todo todo = new Todo("Mark Todo");
        todo.markAsDone();
        assertEquals("[T][X] Mark Todo", todo.toString());
        assertEquals("T | 1 | Mark Todo", todo.toStorageFormat());
    }

    @Test
    public void unmarkTodoTest() {
        Todo todo = new Todo("Unmark Todo");
        todo.markAsUndone();
        assertEquals("[T][ ] Unmark Todo", todo.toString());
        assertEquals("T | 0 | Unmark Todo", todo.toStorageFormat());
    }

    @Test
    public void isEqualDateTodoTest() {
        Todo todo = new Todo("Equal Date");
        assertFalse(todo.isEqualDate(LocalDate.parse("2022-12-30")));
    }

    @Test
    public void isMatchingKeywordsDeadlineTest() {
        Todo todo = new Todo("Matching Todo Keywords");
        assertTrue(todo.isMatchingKeyword("do"));
        assertFalse(todo.isMatchingKeyword("dont"));
    }
}
