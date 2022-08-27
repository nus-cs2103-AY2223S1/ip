package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void testCreateDeadline() {
        Deadline deadline = new Deadline("Finish CS2103T iP", "by 2022-01-01");
        assertEquals(deadline.toString(), "[D][ ] Finish CS2103T iP (by 2022-01-01)");
    }

    @Test
    public void testMarkDeadline() {
        Deadline deadline = new Deadline("Finish CS2103T iP", "by 2022-01-01");
        deadline.markDone();
        assertEquals(deadline.toString(), "[D][X] Finish CS2103T iP (by 2022-01-01)");
    }

    @Test
    public void testLoadDeadline() {
        Deadline deadline = Deadline.fromSaveString("D,0,\"Finish CS2103T iP\",\"2022-01-01\"");
        assertEquals(deadline.toString(), "[D][ ] Finish CS2103T iP (by 2022-01-01)");
    }

    @Test
    public void testLoadInvalidDeadline1() {
        assertThrows(DukeException.class, () -> Deadline.fromSaveString("D,0,asda"));
    }

    @Test
    public void testLoadInvalidDeadline2() {
        assertThrows(DukeException.class, () -> Deadline.fromSaveString(""));
    }
}
