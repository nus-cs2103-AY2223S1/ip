package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    /**
     * Tests save format conversion method in Event class with 2 valid inputs.
     * Test should return successfully if the converted string matches the expected output.
     */
    @Test
    public void saveFormat_saveFormatConversion_success() {
        Event t1 = new Event("task1", LocalDateTime.of(2000,
                12, 14, 5, 30));
        Event t2 = new Event("task2", LocalDateTime.of(2022,
                12, 14, 23, 59));
        t1.markDone();
        assertEquals("E | 1 | task1 | Dec 14 2000 0530", t1.saveFormat());
        assertEquals("E | 0 | task2 | Dec 14 2022 2359", t2.saveFormat());
    }
}
