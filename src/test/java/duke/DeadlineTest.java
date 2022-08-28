package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void printDeadlineTest() {
        assertEquals("D |   | homework | 1 Jan 2022",
                new Deadline("homework", false, "1 Jan 2022").toString());
    }

    @Test
    public void markDeadlineTest() {
        Task deadline = new Deadline("read book", false, "1 Jan 2022");
        deadline.markAsDone();
        assertEquals("D | X | read book | 1 Jan 2022", deadline.toString());
    }

    @Test
    public void unmarkDeadlineTest() {
        Task deadline = new Deadline("read book", true, "1 Jan 2022");
        deadline.markAsUndone();
        assertEquals("D |   | read book | 1 Jan 2022", deadline.toString());
    }

}
