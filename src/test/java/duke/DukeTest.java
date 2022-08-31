package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void testRun() {
        Duke d = new Duke();
        try{
            d.run();
        } catch (Exception e) {
            fail();
        }
    }
}

