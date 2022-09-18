package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TaskTest {
    @Test
    @DisplayName("Check Get Local Date Time")
    public void checkGetLocalDateTime() {
        LocalDateTime output = null;
        try {
            output = Task.getLocalDateTime("27/10/2000 1000");
            assertEquals(LocalDateTime.of(2000, Month.OCTOBER, 27, 10, 0), output);
        } catch (DukeException e) {
            fail(e.getMessage());
        }

    }
}
