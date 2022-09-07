package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void testGetResponse() {
        Duke duke = new Duke();
        assertEquals(duke.getResponse("bla"), "Invalid Command");
    }
}

