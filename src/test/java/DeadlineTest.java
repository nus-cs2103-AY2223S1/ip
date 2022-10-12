import duke.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void convertToDate_success() {
        assertEquals(true, new Deadline("read book", "2019-12-01").convertToDate());
        assertEquals(true, new Deadline("return book", "2020-01-01").convertToDate());
        assertEquals(true, new Deadline("go to school", "2020-02-01").convertToDate());
    }

    @Test
    public void newDeadlineTest() {
        Deadline deadline = new Deadline("finish homework", "2019-12-01");
        assertEquals(deadline.toString(), "[D][ ] finish homework (by: 2019-12-01)");
    }

    @Test
    public void markDeadlineTest() {
        Deadline deadline = new Deadline("finish homework", "2019-12-01");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] finish homework (by: 2019-12-01)");
    }
}
