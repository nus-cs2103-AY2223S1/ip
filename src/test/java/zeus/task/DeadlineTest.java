package zeus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void testDeadlineString() {
        Deadline e = new Deadline("Submit report", "07 Sept 2022");
        assertEquals("[D][ ] Submit report (by: 07 Sept 2022)", e.toString(), "toString() method works");

        e.markAsDone();
        assertEquals("[D][X] Submit report (by: 07 Sept 2022)", e.toString(), "markAsDone() method works");
    }

    @Test
    void testDeadlineLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime ld = LocalDateTime.parse("2022-09-07 2359", formatter);
        Deadline deadline = new Deadline("Submit report", ld);
        assertEquals("[D][ ] Submit report (by: Sep 7 2022 2359)", deadline.toString(), "toString() method works");

        deadline.markAsDone();
        assertEquals("[D][X] Submit report (by: Sep 7 2022 2359)", deadline.toString(), "markAsDone() method works");
    }
}
