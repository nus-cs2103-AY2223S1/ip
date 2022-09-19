package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void testToString() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }
}
