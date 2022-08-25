package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void test_toString() throws Exception {
        Todo todo = new Todo("test");
        assertEquals("[T] [ ] test", todo.toString());
    }
}
