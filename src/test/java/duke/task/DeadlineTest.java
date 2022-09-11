package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    private final LocalDateTime dummyDateTime = LocalDateTime.of(
            2022, 9, 21, 9,0);

    @Test
    public void testMarkDone_executeMarkDone_deadlineIsDone() {
        assertTrue(new Deadline("dummy", dummyDateTime).markDone().isDone());
    }

    @Test
    public void testUnmarkDone_executeUnmarkDone_deadlineIsNotDone() {
        assertFalse(new Deadline("dummy", dummyDateTime).unmarkDone().isDone());
    }

    @Test
    public void testToString_executeToString_outputStringIsCorrect() {
        assertEquals(new Deadline("dummy", dummyDateTime).toString(),
                "[D][ ] dummy (by: Sep 21 2022 09:00)");
    }
}
