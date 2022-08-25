package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getType_DReturned() {
        Deadline test = new Deadline("test", "2000-01-01");
        assertEquals("D", test.getType());
    }
}
