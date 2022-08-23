package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addDeadline() {
        TaskList t = new TaskList();
        t.add("deadline test /by 2022-12-12", Duke.Type.DEADLINE);
        assertEquals("1.[D][ ] test 12 DECEMBER 2022\n", t.toString());
    }
}
