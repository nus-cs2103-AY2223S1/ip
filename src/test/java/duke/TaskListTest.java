package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void testGetNumberOfTasks() {
        assertEquals(0, new TaskList().getNumTasks());
    }

}
