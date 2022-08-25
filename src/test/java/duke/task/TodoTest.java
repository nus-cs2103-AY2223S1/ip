package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getType_TReturned() {
        Todo test = new Todo("test");
        assertEquals("T", test.getType());
    }
}
