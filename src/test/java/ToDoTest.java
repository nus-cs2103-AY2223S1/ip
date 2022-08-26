package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void descriptionTest() {
        String description = "assignment";
        ToDo test = new ToDo(description);
        assertEquals("[T][ ]assignment", test.toString());
    }
}
