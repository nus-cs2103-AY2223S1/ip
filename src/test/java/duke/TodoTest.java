package duke;

import duke.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoTest {
    @Test
    @DisplayName("Test assert Todo description")
    public void Description() {
        Todo todo1 = new Todo("Read book");
        Todo todo2= new Todo("Read book");
        assertEquals(todo1.toString(), todo2.toString());
    }
    @Test
    @DisplayName("Test assert Todo encode")
    public void encode() {
        Todo todo1 = new Todo("Read book");
        Todo todo2= new Todo("Read book");
        assertEquals(todo1.encode(), todo2.encode());
    }

}
