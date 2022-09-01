package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Deadline test class to test deadline object methods
 */
public class DeadlineTest {

    /**
     * Test for string representation of a deadline task
     */
    @Test
    public void addDeadlineTest() {
        Deadline deadline = new Deadline("Assignment 1", LocalDateTime.parse("23-Aug-2022 18:00",
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")));
        assertEquals("[D][ ] Assignment 1 (by: 2022/08/23 06.00PM)", deadline.toString());
    }

    /**
     * Test for marking a deadline task as done
     */
    @Test
    public void addDeadlineTestMarked() {
        Deadline deadline = new Deadline("Tester1", LocalDateTime.parse("10-Jan-2020 12:30",
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")));
        deadline.setMarked();
        assertEquals("[D][X] Tester1 (by: 2020/01/10 12.30PM)", deadline.toString());
    }
}
