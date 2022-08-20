package ren;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenExceptionTest {
    @Test
    public void toString_message_success() {
        String message = "Test";
        assertEquals(" >_< Apologies! Test\n", new RenException(message).toString());
    }

    @Test
    public void toString_empty_success() {
        assertEquals(" >_< Apologies! \n", new RenException("").toString());
    }
}
