package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoTest {

    @Test
    public void newTodo_unmarked_success() {
        try {
            Todo todo = new Todo("UwU");
            assertEquals("[T][ ] UwU", todo.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void newTodo_marked_success() {
        try {
            Todo todo = new Todo("UwU", true);
            assertEquals("[T][✔] UwU", todo.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void newTodo_emptyDesc_exceptionThrown() {
        try {
            Todo todo = new Todo("");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a Task must not be empty.", e.getMessage());
        }
    }

    @Test
    public void newTodo_nullValueDesc_exceptionThrown() {
        try {
            Todo todo = new Todo(null);
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a Task must not be empty.", e.getMessage());
        }
    }

    @Test
    public void toStorageString_unmarked_success() {
        try {
            Todo todo = new Todo("this is a test 😫");
            assertEquals("this is a test 😫 / false / T", todo.toStorageString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toStorageString_marked_success() {
        try {
            Todo todo = new Todo("this is a marked test ✔", true);
            assertEquals("this is a marked test ✔ / true / T", todo.toStorageString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void setDone_setToTrue_success() {
        try {
            Todo todo = new Todo("prev unmarked", false);
            assertEquals(false, todo.getIsDone());
            todo.setDone(true);
            assertEquals(true, todo.getIsDone());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void setDone_setToFalse_success() {
        try {
            Todo todo = new Todo("prev marked", true);
            assertEquals(true, todo.getIsDone());
            todo.setDone(false);
            assertEquals(false, todo.getIsDone());
        } catch (DukeException e) {
            fail();
        }
    }
}
