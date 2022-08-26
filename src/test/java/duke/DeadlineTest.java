package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse("12/12/2020 18:00", formatter);

    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("concert", date);
        assertEquals("[D][ ]concert (by: Dec 12 2020 06:00 PM)" + "\n", deadline.toString());
    }
}

