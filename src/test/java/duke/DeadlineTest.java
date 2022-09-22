package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testGetTime() {
        assertEquals("1200",
                new Deadline("do deadline", "25/09/2022 1200", false).getTime());
    }
}
