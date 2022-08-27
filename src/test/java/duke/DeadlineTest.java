package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Tests the Deadline class.
 */
public class DeadlineTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDateTime date = LocalDateTime.parse("12/12/2020 18:00", formatter);

    /**
     * Tests the toString method in Deadline class.
     */
    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("concert", date);
        assertEquals("[D][ ]concert (by: Dec 12 2020 06:00 PM)" + "\n", deadline.toString());
    }
}

