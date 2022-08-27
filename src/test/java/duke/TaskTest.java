package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {
    @Test
    public void testCreateTask() {
        Task task = new Task("Finish CS2103T iP");
        assertEquals(task.toString(), "[T][ ] Finish CS2103T iP");
    }

    @Test
    public void testMarkTask() {
        Task task = new Task("Finish CS2103T iP");
        task.markDone();
        assertEquals(task.toString(), "[T][X] Finish CS2103T iP");
    }

    @Test
    public void testLoadTask() {
        Task task = Task.fromSaveString("T,0,\"Finish CS2103T iP\"");
        assertEquals(task.toString(), "[T][ ] Finish CS2103T iP");
    }

    @Test
    public void testLoadInvalidTask1() {
        assertThrows(DukeException.class, () -> Task.fromSaveString("T,0,"));
    }

    @Test
    public void testLoadInvalidTask2() {
        assertThrows(DukeException.class, () -> Task.fromSaveString(""));
    }
}
