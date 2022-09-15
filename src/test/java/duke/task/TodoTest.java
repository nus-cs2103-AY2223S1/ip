package duke.task;

import duke.dukeexception.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void testPrintedTodo() {
        try {
            assertEquals("[T][ ] return book",
                    new ToDo("todo return book").printTask());
            assertEquals("[T][ ] return book",
                    new ToDo("t return book").printTask());
        } catch (DukeException e) {
            fail();
        }
    }
}
