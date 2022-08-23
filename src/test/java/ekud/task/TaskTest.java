package ekud.task;

import ekud.exception.EkudException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void toFileFormat_todo_correctFormat() {
        Task task = new ToDo("Description");
        assertEquals(task.toFileFormat(), "T|0|Description");
    }

    @Test
    public void toFileFormat_deadline_correctFormat() {
        try {
            Task task = new Deadline("Description", "2022-08-12");
            assertEquals(task.toFileFormat(), "D|0|Description|2022-08-12");
        } catch (EkudException exception) {
            fail("Should not throw");
        }
    }

    @Test
    public void toFileFormat_event_correctFormat() {
        Task task = new Event("Description", "tomorrow");
        assertEquals(task.toFileFormat(), "E|0|Description|tomorrow");
    }
}
