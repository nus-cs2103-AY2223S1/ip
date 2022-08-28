import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import task.Task;
import task.Deadline;

public class DeadlineTest {

    @Test
    public void getStatusIcon_unmarked() {
        Task deadline = new Deadline("return book", "21/10/2022 1800");
        deadline.isMark(false);
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    public void getStatusIcon_marked() {
        Task deadline = new Deadline("return book", "21/10/2022 1800");
        deadline.isMark(true);
        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    public void toString_success() {
        Task deadline = new Deadline("return book", "21/10/2022 1800");
        assertEquals("[D][ ] return book (by: Oct 21 2022 18:00:00)", deadline.toString());
    }
}
