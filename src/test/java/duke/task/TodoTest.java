package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoChangeFormatTest_Success() {
        Todo t1 = new Todo("This is a Test Todo");
        Todo t2 = new Todo("This is also a Test Todo");
        t2.mark();
        assertEquals("T |   | This is a Test Todo", t1.changeFormat());
        assertEquals("T | X | This is also a Test Todo", t2.changeFormat());
    }
}
