import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        assertEquals(new Deadline("Return book", "2022-08-25").toString(),
                "[D][ ] Return book (by: Aug 25 2022)");
    }

    @Test
    public void markDeadlineTest() {
        Deadline deadline = new Deadline("Return book", "2022-08-25");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] Return book (by: Aug 25 2022)");
    }

    @Test
    public void unmarkDeadlineTest() {
        Deadline deadline = new Deadline("Return book", "2022-08-25");
        deadline.markAsDone();
        deadline.markAsUndone();
        assertEquals(deadline.toString(), "[D][ ] Return book (by: Aug 25 2022)");
    }
}
