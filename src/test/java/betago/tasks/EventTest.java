package betago.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import betago.DukeException;




public class EventTest {

    @Test
    public void newEventTest() {
        try {
            Event temp = new Event("do this task", "2012-01-21 1800");
            assertEquals("[E][ ] do this task (at: Jan 21 2012 0600PM)", temp.toString());
        } catch (DukeException e) {
            //This should not happen with the above input.
        }
    }
}
