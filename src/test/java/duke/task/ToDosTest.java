package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
