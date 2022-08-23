package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() {
        Deadline d = new Deadline("Assignment 1", LocalDate.parse("23 Aug 2022",
                DateTimeFormatter.ofPattern("dd MMM uuuu")));
        assertEquals("[D][ ] Assignment 1 (by: 2022-08-23)", d.toString());
    }
}
