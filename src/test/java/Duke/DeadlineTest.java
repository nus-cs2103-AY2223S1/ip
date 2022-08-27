import Duke.Deadline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    @Test
    public void deadlineString() {
        Deadline d = new Deadline("finish homework", "2022-06-23");
        assertEquals("[D][ ] finish homework (by: Jun 23 2022)", d.toString());
    }
}
