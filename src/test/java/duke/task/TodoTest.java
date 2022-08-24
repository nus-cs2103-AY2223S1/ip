package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {
    private Todo todo;

    @BeforeEach
    public void setUp() {
        todo = new Todo("borrow book");
    }

    @Test
    public void getOtherData_returnNothing() {
        assertEquals("", todo.getOtherData());
    }

    @Test
    public void toString_returnString() {
        assertEquals("[T][ ] borrow book", todo.toString());
    }
}
