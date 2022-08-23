package zeus.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineString(){
        Deadline e = new Deadline("Submit report", "07 Sept 2022");
        assertEquals("[D][ ] Submit report (by: 07 Sept 2022)", e.toString(), "toString() method works");

        e.markAsDone();
        assertEquals("[D][X] Submit report (by: 07 Sept 2022)", e.toString(), "markAsDone() method works");
    }

    @Test
    void testDeadlineLocalDate() {
        LocalDate ld = LocalDate.parse("2022-09-07");
        Deadline e = new Deadline("Submit report", ld);
        assertEquals("[D][ ] Submit report (by: Sep 7 2022)", e.toString(), "toString() method works");

        e.markAsDone();
        assertEquals("[D][X] Submit report (by: Sep 7 2022)", e.toString(), "markAsDone() method works");
    }
}
