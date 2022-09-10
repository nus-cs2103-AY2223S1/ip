package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void testToStringConversion() {
        Deadline test1 = new Deadline("testing", LocalDate.parse("2020-11-12"));
        assertEquals("[D][ ] testing (by: Nov 12 2020)", test1.toString());
        test1.isDoneSetter(true);
        assertEquals("[D][X] testing (by: Nov 12 2020)", test1.toString());
    }

    @Test
    public void testToStorageStringConversion() {
        Deadline test1 = new Deadline("testing", LocalDate.parse("2020-11-12"));
        assertEquals("D|0|testing|2020-11-12", test1.toStorageString());
        test1.isDoneSetter(true);
        assertEquals("D|1|testing|2020-11-12", test1.toStorageString());
    }
}
