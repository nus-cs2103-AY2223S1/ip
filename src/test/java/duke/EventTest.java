package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void DeadlineStringTest(){
        String actualString = new Event("Testing123", "2022-08-12").toString();
        String expectedString = "[E][ ] Testing123 (at: Aug 12 2022)";

        assertEquals(expectedString, actualString);
    }


}