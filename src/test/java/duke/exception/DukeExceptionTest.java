package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
