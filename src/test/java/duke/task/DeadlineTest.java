package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static final String DEADLINE_DESCRIPTION = "return book";
    private static final LocalDateTime DEADLINE_TIMING = LocalDateTime.parse("2022-09-10 11:11",
            Task.DATE_TIME_FORMATTER);
    private static final Deadline DEADLINE = new Deadline(DEADLINE_DESCRIPTION, DEADLINE_TIMING);

    @Test
    public void encode_deadlineObject_success() {
        String expectedOutput = "D | 0 | " + DEADLINE_DESCRIPTION + " | 2022-09-10T11:11";

        assertEquals(expectedOutput, DEADLINE.encode());
    }

    @Test
    public void testStringConversion() {
        String expectedOutput = "[D][ ] " + DEADLINE_DESCRIPTION + " (by: 10-Sep-22 11:11)";
        assertEquals(expectedOutput, DEADLINE.toString());
    }
}
