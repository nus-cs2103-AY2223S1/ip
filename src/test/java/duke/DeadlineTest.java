package Duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] homework(by: May 22 2019)", new Deadline("homework", LocalDate.parse("2019-05-22")).toString());
    }

}
