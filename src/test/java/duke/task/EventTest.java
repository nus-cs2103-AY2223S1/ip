package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import duke.dukeexception.DukeException;
public class EventTest {
    @Test
    public void testPrintTaskEvent() {
        try {
            assertEquals("[E][ ] return book  (on:Feb 02 2022)",
                    new Event("event return book on 2/2/2022 1800").printTask());
            assertEquals("[E][ ] return book  (on: monday)",
                    new Event("e return book on monday").printTask());
        } catch (DukeException e) {
            fail();
        }
    }
}
