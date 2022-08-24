package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void createEvent_noMarkSpecified_unmarkedEventReturned() {
        try {
            Event event = new Event("Test", "2022-02-02");
            assertEquals("[E][ ] Test (at: Feb 2 2022)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void createEvent_eventUnmarked_unmarkedEventReturned() {
        try {
            Event event = new Event("Test", "2022-04-04", false);
            assertEquals("[E][ ] Test (at: Apr 4 2022)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void createEvent_eventMarked_markedEventReturned() {
        try {
            Event event = new Event("Test", "2023-03-03", true);
            assertEquals("[E][X] Test (at: Mar 3 2023)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toSaveFileString_eventUnmarked_stringRepresentationMatch() {
        try {
            Event event = new Event("Test", "2022-02-02", false);
            assertEquals("[E] @ [ ] @ Test @ 2022-02-02", event.toSaveFileString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toSaveFileString_eventMarked_stringRepresentationMatch() {
        try {
            Event event = new Event("Test", "2022-02-02", true);
            assertEquals("[E] @ [X] @ Test @ 2022-02-02", event.toSaveFileString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markAsDoneTest() {
        try {
            Event event = new Event("Test", "2022-02-02", false);
            event.markAsDone();
            assertEquals("[E][X] Test (at: Feb 2 2022)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markAsUndoneTest() {
        try {
            Event event = new Event("Test", "2022-02-02", true);
            event.markAsUndone();
            assertEquals("[E][ ] Test (at: Feb 2 2022)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
