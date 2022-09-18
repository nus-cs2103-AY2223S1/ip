package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testGetTask() {
        Deadline d = new Deadline("complete iP /by 19-09-2022");
        assertEquals("[D][ ]complete iP (by: 19 Sep 2022)", d.getTask());
    }
}
