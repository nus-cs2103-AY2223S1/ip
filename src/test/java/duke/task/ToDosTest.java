package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T] pack bag [X]", new ToDos("pack bag", true).toString());
    }
}
