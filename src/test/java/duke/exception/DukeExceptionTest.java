package duke.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void newExceptionTest() {
        assertEquals(new DukeException("Test").getMessage(), "Hang on! Test");
    }

    @Test
    public void exceptionTypeTest() {
        assert new DukeException("Test") instanceof Exception;
    }
}
