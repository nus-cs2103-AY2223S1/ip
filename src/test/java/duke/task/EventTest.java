package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void convertToSaveFormat_saveFormatConversion_success() {
        Event t1 = new Event("task1", LocalDateTime.of(2012, 3, 4, 5, 6),
                LocalDateTime.of(2012, 3, 4, 5, 7));
        Event t2 = new Event("task2", LocalDateTime.of(2065, 4, 3, 2, 1),
                LocalDateTime.of(2065, 4, 3, 2, 2));
        t1.markAsDone();
        assertEquals("E | 1 | task1 | 2012-03-04 0506 to 2012-03-04 0507", t1.convertToSaveFormat());
        assertEquals("E | 0 | task2 | 2065-04-03 0201 to 2065-04-03 0202", t2.convertToSaveFormat());
    }
}
