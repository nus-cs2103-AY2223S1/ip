package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void saveText_normalInput_unmarked() {
        Task todo = new Todo("sample task");
        assertEquals("T|0|sample task", todo.saveText());
    }

    @Test
    void saveText_normalInput_marked() {
        Task todo = new Todo("sample task");
        todo.mark();
        assertEquals("T|1|sample task", todo.saveText());
    }
}
