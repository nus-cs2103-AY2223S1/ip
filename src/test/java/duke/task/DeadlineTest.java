package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        LocalDate by = LocalDate.parse("2022-11-11");
        Deadline newDeadline = new Deadline("Deadline", by);
        assertEquals("[D][ ] Deadline (by: Nov 11 2022)", newDeadline.toString());
    }

    @Test
    public void toSimpleStringTest() {
        LocalDate by = LocalDate.parse("2022-11-11");
        Deadline newDeadline = new Deadline("Deadline", by);
        assertEquals("D | 0 | Deadline | 2022-11-11", newDeadline.toSimpleString());
    }

}
