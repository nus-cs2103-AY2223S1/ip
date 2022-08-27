package duke.tasks;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void encodeTest(){
        Task todo = null;
        try {
            todo = new Todo("Description");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(todo.encode(), "T | 0 | Description");
    }

    @Test
    public void toStringTest(){
        Task todo = null;
        try {
            todo = new Todo("Description");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(todo.toString(), "[T][ ] Description");
    }
}