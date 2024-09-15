package scruffles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline d = new Deadline("play with tanks", LocalDate.parse("1989-06-04"));
        assertEquals("[D][ ] play with tanks (by: 4 JUNE 1989)", d.toString());
    }
}
