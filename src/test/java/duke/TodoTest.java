package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getTaskTypeTest() {
        assertEquals(new Todo("read book").getTaskType(), "T");
    }
}
