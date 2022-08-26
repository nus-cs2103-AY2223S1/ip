package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getType_dReturned() {
        Deadline test = new Deadline("test", "2000-01-01");
        assertEquals("D", test.getType());
    }
}
