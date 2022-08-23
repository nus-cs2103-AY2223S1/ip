package Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void addToDoTest() {
        Todo td = new Todo("task 1");
        assertEquals("[T][ ] task 1", td.toString());
    }
}
