package duke.tools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {

    @Test
    public void getDateTest() {
        assertEquals("12 Jun 2022", new DateTime("2022-06-12").getDate());
    }
}
