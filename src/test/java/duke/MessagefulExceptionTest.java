package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessagefulExceptionTest {
    @Test
    public void getMessage_smokeTest() {
        MessagefulException me = new MessagefulException("A", "B");
        assertEquals("A", me.getMessage());
    }

    @Test
    public void getHint_smokeTest() {
        MessagefulException me = new MessagefulException("A", "B");
        assertEquals("B", me.getHint());
    }
}
