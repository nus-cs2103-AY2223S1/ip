import duke.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {

    @Test
    public void getTaskType() {
        assertEquals("T", new Todo("Create memo"));
    }

    @Test void getStringConversion() {
        assertEquals("[T] [ ] Create memo", new Todo("Create memo"));
    }
}
