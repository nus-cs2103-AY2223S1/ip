package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() {
        Deadline deadline = new Deadline("Assignment 1", LocalDateTime.parse("23-Aug-2022 18:00",
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")));
        assertEquals("[D][ ] Assignment 1 (by: 2022/08/23 06.00PM)", deadline.toString());
    }
}
