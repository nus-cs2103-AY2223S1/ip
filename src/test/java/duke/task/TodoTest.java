package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void getType_tReturned() {
        Todo test = new Todo("test");
        assertEquals("T", test.getType());
    }
}
