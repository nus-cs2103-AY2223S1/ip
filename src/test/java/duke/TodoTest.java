package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToString() {
        assertEquals("[T][ ] Create Something", new ToDo("Create Something").toString());
    }
}
