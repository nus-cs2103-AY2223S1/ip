package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", new Task("").getStatusIcon());
        Task t = new Task("");
        t.markAsDone();
        assertEquals("X", t.getStatusIcon());
    }
}
