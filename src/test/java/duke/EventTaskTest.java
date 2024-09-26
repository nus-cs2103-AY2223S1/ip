package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTaskTest {

    @Test
    public void createEventTaskTest() throws DukeException {
        EventTask eventTask = new EventTask("work ", "2022-12-20 1800");
        assertEquals("[E][ ] work  (at: Dec 20 2022 6:00 PM)", eventTask.toString());
    }
}
