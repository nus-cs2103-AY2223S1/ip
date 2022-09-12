package sally.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void deadlineTestGivenString() {
        Deadline deadlineTest = new Deadline("deadline test", "byString");
        assertEquals("[D][ ] deadline test (by: byString)", deadlineTest.toString(), "toString() method works");

        deadlineTest.markAsDone();
        assertEquals("[D][X] deadline test (by: byString)", deadlineTest.toString(), "markAsDone() method works");

        deadlineTest.markAsUndone();
        assertEquals("[D][ ] deadline test (by: byString)", deadlineTest.toString(), "markAsUndone() method works");
    }

    @Test
    void deadlineTestGivenDate() {
        Deadline deadlineTest = new Deadline("deadline test", LocalDate.of(2022, Month.APRIL, 1));
        assertEquals("[D][ ] deadline test (by: Apr 01 2022)", deadlineTest.toString(), "toString() method works");

        deadlineTest.markAsDone();
        assertEquals("[D][X] deadline test (by: Apr 01 2022)", deadlineTest.toString(), "markAsDone() method works");

        deadlineTest.markAsUndone();
        assertEquals("[D][ ] deadline test (by: Apr 01 2022)", deadlineTest.toString(), "markAsUndone() method works");
    }
}
