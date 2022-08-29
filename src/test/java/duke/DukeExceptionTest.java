package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void testDukeExceptionMessageCorrect(){
        DukeException testError = new DukeException("Duke is not functioning properly");
        assertEquals("Duke is not functioning properly", testError.getMessage());
    }
}
