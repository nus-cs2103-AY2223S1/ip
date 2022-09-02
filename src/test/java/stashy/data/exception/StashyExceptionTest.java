package stashy.data.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for StashyException.
 */
public class StashyExceptionTest {
    @Test
    public void errorMessageTest() {
        assertEquals("Test message", (new StashyException("Test message")).getMessage());
    }
}
