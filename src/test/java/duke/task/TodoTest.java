package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals(new Todo("read book").toString(), "[T][ ] read book");
    }
}
