package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void create_notDoneString_createdCorrectly() {
        Todo todo = Todo.create("0", "abc");
        assertEquals("[T][ ] abc", todo.toString());
    }

    @Test
    public void create_doneString_createdCorrectly() {
        Todo todo = Todo.create("1", "abc");
        assertEquals("[T][X] abc", todo.toString());
    }

    @Test
    public void fileFormat_notDone_formattedCorrectly() {
        Todo todo = Todo.create("0", "abc");
        assertEquals("T | 0 | abc", todo.getFileFormat());
    }

    @Test
    public void fileFormat_done_formattedCorrectly() {
        Todo todo = Todo.create("1", "abc");
        assertEquals("T | 1 | abc", todo.getFileFormat());
    }

    @Test
    public void markAsDone_done_changedCorrectly() {
        Todo todo = Todo.create("0", "abc");
        todo.markAsDone();
        assertEquals("[T][X] abc", todo.toString());
    }

    @Test
    public void markAsUndone_undone_changedCorrectly() {
        Todo todo = Todo.create("1", "abc");
        todo.markAsUndone();
        assertEquals("[T][ ] abc", todo.toString());
    }
}
