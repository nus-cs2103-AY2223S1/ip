package duke.task;

import duke.util.DateAndTimeFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        assertEquals("[D][ ] Return book (by: Aug 25 2022)",
                new Deadlines("Return book", "2022-08-25",
                DateAndTimeFormatter.validateAndParse("2022-08-25")).toString());
    }

    @Test
    public void markDeadlineTest() {
        Deadlines deadline = new Deadlines("Return book", "2022-08-25",
                DateAndTimeFormatter.validateAndParse("2022-08-25"));
        deadline.markAsDone();
        assertEquals("[D][X] Return book (by: Aug 25 2022)", deadline.toString());
    }

    @Test
    public void unmarkDeadlineTest() {
        Deadlines deadline = new Deadlines("Return book", "2022-08-25",
                DateAndTimeFormatter.validateAndParse("2022-08-25"));
        deadline.markAsDone();
        deadline.markAsUndone();
        assertEquals("[D][ ] Return book (by: Aug 25 2022)", deadline.toString());
    }
}
