package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;

public class EventTest {
    private Event event;

    @BeforeEach
    public void setUp() {
        try {
            event = new Event("bookfair", Parser.strToDate("27 aug 2022 4:30pm"));
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void getOtherData_returnCorrectTime() {
        assertEquals("2022-08-27T16:30", event.getOtherData());
    }

    @Test
    public void toString_returnString() {
        assertEquals("[E][ ] bookfair (at: Aug 27, 2022 4:30 PM)", event.toString());
    }
}
