package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testReturnDescription() throws DukeException {
        Event event = new Event("read book", "2022-04-03T10:52");

        //Test Case 1
        assertEquals("[E][ ] read book (at: Apr 03 2022 @ 10:52)", event.returnDescription());
    }

    @Test
    public void testToWriteFile() throws DukeException {
        Event event = new Event("read book", "2022-04-03T10:52", false);

        //Test Case 1
        assertEquals("E , false , read book , 2022-04-03T10:52", event.toWriteFile());

    }
}
