package duke;

import duke.Deadline;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void newDeadlineTest() {
        Deadline d = new Deadline("soccer", LocalDate.parse("2022-09-08"),LocalTime.parse("09:00"), false);
        assertEquals("[D][O]soccer (by: Sep 8 2022 09:00)",d.toString());
    }

    @Test
    public void newDeadlineMarkTest() {
        Deadline d = new Deadline("soccer", LocalDate.parse("2022-09-08"),LocalTime.parse("09:00"), false);
        d.markAsDone();
        assertEquals("[D][X]soccer (by: Sep 8 2022 09:00)",d.toString());
    }

}
