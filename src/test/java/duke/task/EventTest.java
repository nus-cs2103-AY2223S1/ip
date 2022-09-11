package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private final LocalDateTime dummyDateTime = LocalDateTime.of(
            2022, 9, 21, 9,0);

    @Test
    public void testMarkDone_executeMarkDone_deadlineIsDone() {
        assertTrue(new Event("dummy", dummyDateTime).markDone().isDone());
    }

    @Test
    public void testUnmarkDone_executeUnmarkDone_deadlineIsNotDone() {
        assertFalse(new Event("dummy", dummyDateTime).unmarkDone().isDone());
    }

    @Test
    public void testToString_executeToString_outputStringIsCorrect() {
        assertEquals(new Event("dummy", dummyDateTime).toString(),
                "[E][ ] dummy (at: Sep 21 2022 09:00)");
    }
}
