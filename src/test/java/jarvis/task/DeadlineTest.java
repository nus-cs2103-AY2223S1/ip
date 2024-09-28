package jarvis.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        assertEquals("[D][ ] return book (by: Aug 25 2022)", new Deadline("return book", "25/08/2022").toString());
    }
}
