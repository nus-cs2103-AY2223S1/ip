package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    @Test
    public void ValidARGs() {
        Deadlines dummyDeadline = new Deadlines("sample");
        String expectedOutcome = "sample";
        String testOutcome = dummyDeadline.getDeadLineTask();
        assertEquals(testOutcome, expectedOutcome);
    }

    @Test
    public void invalidValidARGs() {
        Deadlines dummyDeadline = new Deadlines("sample");
        String expectedOutcome = "Won't Work";
        String testOutcome = dummyDeadline.getDeadLineTask();
        assertNotEquals(testOutcome, expectedOutcome);
    }
}
