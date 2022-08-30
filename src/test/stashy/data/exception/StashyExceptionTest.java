package test.stashy.data.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StashyExceptionTest {
    @Test
    public void errorMessageTest() {
        assertEquals("Test message", (new StashyException("Test message")).getMessage());
    }
}