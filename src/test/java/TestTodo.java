import chad.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTodo {
    @Test
    public void testToString() {
        Todo t;
        t = new Todo("Do CS2103");
        assertEquals("[T][ ] Do CS2103", t.toString());

        t = new Todo("Watch Tiktok");
        assertEquals("[T][ ] Watch Tiktok", t.toString());

        t = new Todo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", t.toString());
    }
}
