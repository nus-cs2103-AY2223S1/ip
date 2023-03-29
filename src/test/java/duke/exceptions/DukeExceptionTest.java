package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {

    @Test
    public void exceptionMessageTest() {
        assertEquals("Shiba says: \"OOPS! Test message\" ☹", (new DukeException("Test message")).getMessage());
    }

}
