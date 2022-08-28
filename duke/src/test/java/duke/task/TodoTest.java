package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void constructor_acceptableConstructor_success() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
        assertEquals("[T][ ] read book", new Todo("read book", false).toString());
        assertEquals("[T][X] read book", new Todo("read book", true).toString());
    }
}
