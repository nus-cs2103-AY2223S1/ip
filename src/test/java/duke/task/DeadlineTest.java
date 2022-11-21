package duke.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void  toStringTest() {
        Deadline d = new Deadline("Write poem", LocalDate.parse("2000-08-07"));
        assertEquals(d.toString(), "[D][ ] Write poem (by: Aug 7 2000)");
    }
}
