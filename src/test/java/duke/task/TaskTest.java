package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.common.DukeException;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void testDecode() {
        try {
            Task task = Task.decode("T | 1 | Buy milk");
            assertEquals("[T][X] Buy milk", task.toString());
            assertEquals("T | 1 | Buy milk", task.encode());
        } catch (Exception e) {
            fail();
        }

        try {
            Task task = Task.decode("D | 0 | Buy milk | 2020-01-01");
            assertEquals("[D][ ] Buy milk (by: 2020-01-01)", task.toString());
            assertEquals("D | 0 | Buy milk | 2020-01-01", task.encode());
        } catch (Exception e) {
            fail();
        }

        try {
            Task task = Task.decode("E | 1 | Buy milk | 2020-01-01");
            assertEquals("[E][X] Buy milk (at: 2020-01-01)", task.toString());
            assertEquals("E | 1 | Buy milk | 2020-01-01", task.encode());
        } catch (Exception e) {
            fail();
        }

        try {
            Task.decode("E |");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }
}
