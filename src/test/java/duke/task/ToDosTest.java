package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {
    @Test
    public void todosTestToString() {
        ToDos test = new ToDos("test");
        assertEquals("[T][ ] test", test.toString());
    }

    @Test
    public void todosTextFormat() {
        ToDos test = new ToDos("test");
        assertEquals("T|0|test", test.textFormat());
    }
}
