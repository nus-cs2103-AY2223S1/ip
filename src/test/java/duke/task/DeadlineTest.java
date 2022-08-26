package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    /**
     * Tests save format conversion method in Deadline class with 2 valid inputs.
     * Test should return successfully if the converted string matches the expected output.
     */
    @Test
    public void saveFormat_saveFormatConversion_success() {
        Deadline t1 = new Deadline("task1", LocalDateTime.of(2000,
                12, 14, 5, 30));
        Deadline t2 = new Deadline("task2", LocalDateTime.of(2022,
                12, 14, 23, 59));
        t1.markDone();
        assertEquals("D | 1 | task1 | Dec 14 2000 0530", t1.saveFormat());
        assertEquals("D | 0 | task2 | Dec 14 2022 2359", t2.saveFormat());
    }
}
