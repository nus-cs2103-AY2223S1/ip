package bob;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class EventTest {
    @Test
    public void testOne() {
        Task task = new Event("party", LocalDate.parse("2019-12-12"));
        assertEquals("[E][ ] party (at: Dec 12 2019)", task.toString());
    }

    @Test
    public void testTwo() {
        TaskList tasks = new TaskList();
        Task task = new Event("track competition", LocalDate.parse("2022-08-28"));
        tasks.addTask(task);
        tasks.markTask(1, true);
        assertEquals("[E][X] track competition (at: Aug 28 2022)", task.toString());
    }

}
