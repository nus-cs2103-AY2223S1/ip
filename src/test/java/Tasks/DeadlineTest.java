package Tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() {
        Deadline d = new Deadline("test 1", LocalDate.parse("14 Jan 2022",
                DateTimeFormatter.ofPattern("dd MMM uuuu")));
        assertEquals("[D][ ] test 1 (by: 2022-01-14)", d.toString());
    }
}

