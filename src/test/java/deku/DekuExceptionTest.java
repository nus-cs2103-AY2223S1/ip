package deku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DekuExceptionTest {
    @Test
    public void exceptionTest_emptyException_string() {
        assertEquals("AUUUUUGH! ",
                new DekuExceptions("").toString(),
                "Test empty exception");
    }

    @Test
    public void exceptionTest_filledException_string() {
        assertEquals("AUUUUUGH! test test",
                new DekuExceptions("test test").toString(),
                "Test empty exception");
    }
}
