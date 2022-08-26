package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void eventTest(){
        Event test = new Event("event", "eventTime");
        assertEquals("[E][ ] event (at: eventTime)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[E][X] event (at: eventTime)", test.toString(), "markAsDone() method works");
    }
}
