import org.junit.jupiter.api.Test;
import Duke.DukeException;
import Duke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void testIsTodo(){
        try {
            assertEquals(true, new Todo("Description", false).isTodo());
        }
        catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testDukeException(){
        try {
            new Todo("", false);
            fail();
        }
        catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}
