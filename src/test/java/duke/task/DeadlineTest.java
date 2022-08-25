package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        Deadline task = new Deadline("testing", LocalDateTime.parse("11/11/2011 1800", format));
        assertEquals("[D][ ] testing (by: 11/11/2011 1800)", task.toString());
    }
}
