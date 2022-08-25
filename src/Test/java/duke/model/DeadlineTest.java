package duke.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toStorage_success() {
        Deadline deadline = new Deadline("do something", "2022-01-05");
        assertEquals(deadline.toStorage(), "D | 0 | do something | 2022-01-05\n");
    }
}