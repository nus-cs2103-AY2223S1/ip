package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] hi", new Todo("hi").toString());
    }


}
