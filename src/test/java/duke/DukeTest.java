package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void testGetResponse() {
        Duke duke = new Duke();
        assertEquals(duke.getResponse("bla"), "Invalid Command");
    }
}

