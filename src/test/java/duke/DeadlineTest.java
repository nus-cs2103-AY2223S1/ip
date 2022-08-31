package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testGetTextRepresentation() {
        Deadline testDeadline = new Deadline("test", LocalDate.parse("1999-02-02"));
        assertEquals("D|0|test|1999-02-02\n", testDeadline.getTextRepresentation());
    }

    @Test
    public void testStringRepresentation() {
        Deadline testDeadline = new Deadline("test", LocalDate.parse("1999-02-02"));
        assertEquals("[D][ ] test (by: Feb 2 1999)", testDeadline.toString());
    }
}
