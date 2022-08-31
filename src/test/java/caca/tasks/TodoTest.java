package caca.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for Todo.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class TodoTest {

    /**
     * Tests the behaviour of adding a todo.
     */
    @Test
    public void addTodoTest() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book",
                todo.toString());
    }

    /**
     * Tests the behaviour of converting a todo that is marked as done into file format.
     */
    @Test
    public void toFileFormat_todoDone_success() {
        Todo todo = new Todo("return book", true);
        assertEquals("T | X | return book",
                todo.toFileFormat());
    }

    /**
     * Tests the behaviour of converting a todo that is not marked as done into file format.
     */
    @Test
    public void toFileFormat_todoUndone_success() {
        Todo todo = new Todo("borrow book", false);
        assertEquals("T |   | borrow book",
                todo.toFileFormat());
    }
}
