package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {
    @Test
    public void testToStringConversion() {
        Event test1 = new Event("testing", LocalDate.parse("2020-11-12"));
        assertEquals("[E][ ] testing (at: Nov 12 2020)", test1.toString());
        test1.isDoneSetter(true);
        assertEquals("[E][X] testing (at: Nov 12 2020)", test1.toString());
    }

    @Test
    public void testToStorageStringConversion() {
        Event test1 = new Event("testing", LocalDate.parse("2020-11-12"));
        assertEquals("E|0|testing|2020-11-12", test1.toStorageString());
        test1.isDoneSetter(true);
        assertEquals("E|1|testing|2020-11-12", test1.toStorageString());
    }

}
