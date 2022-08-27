package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Todo Class.
 */
public class TodoTest {

    /**
     * Tests the toString method in the Todo class.
     */
    @Test
    public void toStringTest() {
        Todo todo = new Todo("picnic");
        assertEquals("[T][ ] picnic" + "\n", todo.toString());
    }
}
