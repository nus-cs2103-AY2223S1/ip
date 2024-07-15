import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void addTest() {
        assertEquals(new Deadline("return book", "2022-10-29").toString(),
                "[D][ ] return book (by: Oct 29 2022)");
    }

    @Test
    public void markTest() {
        Deadline deadline = new Deadline("return book", "2022-10-29");
        deadline.markTask();
        assertEquals(deadline.toString(),
                "[D][X] return book (by: Oct 29 2022)");
    }

    @Test
    public void unmarkTest() {
        Deadline deadline = new Deadline("return book", "2022-10-29");
        deadline.markTask();
        deadline.unmarkTask();
        assertEquals(deadline.toString(),
                "[D][ ] return book (by: Oct 29 2022)");
    }
}
