package roger.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponseTest {
    @Test
    public void testIsExit() {
        Response exitResponse = new Response("hello", true, false);
        Response nonExitResponse = new Response("hello", false, false);

        assertTrue(exitResponse.isExit());
        assertFalse(nonExitResponse.isExit());
    }

    @Test
    public void testIsException() {
        Response exceptionResponse = new Response("hello", true, true);
        Response nonExceptionResponse = new Response("hello", false, false);

        assertTrue(exceptionResponse.isException());
        assertFalse(nonExceptionResponse.isException());
    }
}
