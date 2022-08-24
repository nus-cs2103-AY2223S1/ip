package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void createEventTest() {
        try {
            Event event = new Event("Test", "2022-02-02");
            assertEquals("[E][ ] Test (at: Feb 2 2022)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void createEventTest2() {
        try {
            Event event = new Event("Test", "2022-04-04", false);
            assertEquals("[E][ ] Test (at: Apr 4 2022)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void createEventTest3() {
        try {
            Event event = new Event("Test", "2023-03-03", true);
            assertEquals("[E][X] Test (at: Mar 3 2023)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void saveFileStringTest() {
        try {
            Event event = new Event("Test", "2022-02-02", false);
            assertEquals("[E] @ [ ] @ Test @ 2022-02-02", event.toSaveFileString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void saveFileStringTest2() {
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
