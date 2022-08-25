package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void getTaskTest() {
        LocalDate date = LocalDate.parse("2019-10-09");
        Deadline deadline = new Deadline("hello", date);


        assertEquals("[D] [ ] hello (by: Oct 09 2019)", deadline.getTask());
    }
}