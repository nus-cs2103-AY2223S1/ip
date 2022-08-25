package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void convertToSaveFormat_saveFormatConversion_success() {
        Deadline t1 = new Deadline("task1", LocalDateTime.of(2012, 3, 4, 5, 6));
        Deadline t2 = new Deadline("task2", LocalDateTime.of(2065, 4, 3, 2, 1));
        t1.markAsDone();
        assertEquals("D | 1 | task1 | 2012-03-04 0506", t1.convertToSaveFormat());
        assertEquals("D | 0 | task2 | 2065-04-03 0201", t2.convertToSaveFormat());
    }
}
