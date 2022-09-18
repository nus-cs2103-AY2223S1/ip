package technical;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import functional.Deadline;
import functional.Event;

/**
 * JUnit test class for SaveLine.
 * @author Nicholas Patrick
 */
public class SaveLineTest {
    /**
     * Tests whether SaveLine can turn into a String and back without any error.
     */
    @Test
    public void toStringAndBackChecks() {
        SaveLine exampleDeadline = new Deadline("Theology",
            LocalDateTime.of(2022, 9, 2, 20, 10, 4)).toData();
        String printedDeadline = exampleDeadline.toString();
        SaveLine readDeadline = SaveLine.of(printedDeadline);
        assertEquals(exampleDeadline, readDeadline);

        SaveLine exampleEvent = new Event("Theology",
            LocalDateTime.of(2022, 9, 2, 20, 10, 4),
            LocalDateTime.of(2023, 10, 11, 12, 13, 14)).toData();
        String printedEvent = exampleEvent.toString();
        SaveLine readEvent = SaveLine.of(printedEvent);
        assertEquals(exampleEvent, readEvent);

        exampleEvent.setKeyValue("startTime", "something else");

        assertNotEquals(exampleEvent, readEvent);
    }
}
