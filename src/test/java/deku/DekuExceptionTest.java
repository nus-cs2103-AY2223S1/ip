package deku;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DekuExceptionTest {
    @Test
    public void ExceptionTest_emptyException_String() {
        assertEquals("AUUUUUGH! ",
                new DekuExceptions("").toString(),
                "Test empty exception");
    }

    @Test
    public void ExceptionTest_filledException_String() {
        assertEquals("AUUUUUGH! test test",
                new DekuExceptions("test test").toString(),
                "Test empty exception");
    }
}
