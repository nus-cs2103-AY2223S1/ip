package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
    LocalDate date = LocalDate.now();
    Event testEvent = new Event("test",date);

    @Test
    public void eventTestDone(){
        assertFalse(testEvent.getStatus());
    }

    @Test
    public void eventTestSetDone(){
        testEvent.setDone();
        assertTrue(testEvent.getStatus());
    }
}
