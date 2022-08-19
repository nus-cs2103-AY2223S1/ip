package Tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void addToDoTest() {
        ToDo td = new ToDo("test 1");
        assertEquals("[T][ ] test 1", td.toString());
    }
}
