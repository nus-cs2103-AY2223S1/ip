package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class EventsTest {

    @Test
    public void ValidARGs() {
        Events dummyEvent = new Events("sample");
        String expectedOutcome = "sample";
        String testOutcome = dummyEvent.getEventsDescription();
        assertEquals(testOutcome,expectedOutcome);
    }
    @Test
    public void invalidValidARGs() {
        Events dummyEvent = new Events("sample");
        String expectedOutcome = "Won't Work";
        String testOutcome = dummyEvent.getEventsDescription();
        assertNotEquals(testOutcome,expectedOutcome);
    }
}
