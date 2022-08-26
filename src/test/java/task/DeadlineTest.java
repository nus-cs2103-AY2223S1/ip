package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private Task markedTask;
    private Task unmarkedTask;

    @BeforeEach
    public void initializeDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.markedTask = new Deadline("Marked", LocalDateTime.parse("2020-01-05 05:05", formatter), true);
        this.unmarkedTask = new Deadline("Unmarked", LocalDateTime.parse("2020-01-05 05:05", formatter));
    }

    @Test
    public void testMarkedToString() {
        String correct = "[D][X] Marked (by: 5 January 2020 05:05)";
        Assertions.assertEquals(correct, this.markedTask.toString());
    }

    @Test
    public void testUnmarkedToString() {
        String correct = "[D][ ] Unmarked (by: 5 January 2020 05:05)";
        Assertions.assertEquals(correct, this.unmarkedTask.toString());
    }
}
