package scruffles;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline d = new Deadline("play with tanks", LocalDate.parse("1989-06-04"));
        assertEquals("[D][ ] play with tanks (by: 4 JUNE 1989)", d.toString());
    }
}
