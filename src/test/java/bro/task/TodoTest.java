package bro.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_inputString_returnsString() {
        assertEquals("[T][ ] dinner", new Todo("dinner").toString());
    }
}