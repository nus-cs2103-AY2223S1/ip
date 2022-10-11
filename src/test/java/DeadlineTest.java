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
}
