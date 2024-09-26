package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] watch show", new Todo("watch show").toString());
    }

    @Test
    public void savedStringTest() {
        assertEquals("T | 0 | watch show", new Todo("watch show").toSavedString());
    }

}
