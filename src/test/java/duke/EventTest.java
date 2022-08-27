package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toStringTest() {
        assertEquals("1.[E][ ] test(at : Mar 05 2022)", new Event(1, "test", "2022-03-05").toString());
    }

    @Test
    public void markTest() {
        Task task = new Event(1, "test", "2022-03-05");
        task.setDone();
        assertEquals("1.[E][X] test(at : Mar 05 2022)", task.toString());
    }
}
