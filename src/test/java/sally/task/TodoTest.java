package sally.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void todoTest() {
        ToDo todoTest = new ToDo("todo test");
        assertEquals("[T][ ] todo test", todoTest.toString(), "toString() method works");

        todoTest.markAsDone();
        assertEquals("[T][X] todo test", todoTest.toString(), "markAsDone() method works");

        todoTest.markAsUndone();
        assertEquals("[T][ ] todo test", todoTest.toString(), "markAsUndone() method works");
    }
}
