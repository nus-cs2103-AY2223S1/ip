package sakura;

import org.junit.jupiter.api.Test;
import sakura.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void testStringifyTask() {
        Todo test1 = new Todo("Meditation");
        assertEquals("T|0|Meditation", test1.stringifyTask());
    }

    @Test
    void testMarkDone() {
        Todo test2 = new Todo("Meditation");
        test2.markDone();
        assertEquals("T|1|Meditation", test2.stringifyTask());
    }

    @Test
    void testToString() {
        Todo test1 = new Todo("Meditation");
        assertEquals("(TODO)[ ] Meditation", test1.toString());

    }
}
