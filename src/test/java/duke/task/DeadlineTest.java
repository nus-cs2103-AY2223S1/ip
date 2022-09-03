package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    void deadlineTestWithString() {
        Deadline test = new Deadline("deadline", "deadlineBy");
        assertEquals("[D][ ] deadline (by: deadlineBy)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[D][X] deadline (by: deadlineBy)", test.toString(), "markAsDone() method works");
    }

    @Test
    void deadlineTestWithDate() {
        Deadline test = new Deadline("deadline", LocalDateTime.of(2023, Month.JANUARY, 1, 7, 30));
        assertEquals("[D][ ] deadline (by: Jan 01 2023 07:30)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[D][X] deadline (by: Jan 01 2023 07:30)", test.toString(), "markAsDone() method works");
    }
}
