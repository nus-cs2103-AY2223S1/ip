package qoobee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] hand in assignment (by: 15 09 2022 12:00)",
                new Deadline("hand in assignment ", "15/9/2022 1200").toString());
    }

    @Test
    public void dateTime_success() {
        assertEquals("20 01 2000 14:20",
                new Deadline("test", "20/1/2000 1420").getDateTime());
    }

}
