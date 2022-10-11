package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void changeFormat__Success() {
        Deadline d1 = new Deadline("This is a Test Deadline", LocalDateTime.of(2022, 9, 4, 12, 00));
        Deadline d2 = new Deadline("This is also a Test Deadline", LocalDateTime.of(2022, 4, 9, 16, 00));
        d2.mark();
        assertEquals("D |   | This is a Test Deadline | 2022-09-04 1200", d1.changeFormat());
        assertEquals("D | X | This is also a Test Deadline | 2022-04-09 1600", d2.changeFormat());
    }
}
