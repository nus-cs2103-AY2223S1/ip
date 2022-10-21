package duke;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class EventsTest {

    @Test
    public void ValidARGs() {
        Events dummyEvent = new Events("sample");
        String expectedOutcome = "null";
        String testOutcome = dummyEvent.getEvent();
        assertEquals(testOutcome,expectedOutcome);
    }







}
