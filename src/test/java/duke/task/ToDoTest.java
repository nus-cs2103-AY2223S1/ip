package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToDoToString() {
        assertEquals("[T] [ ] testing", new ToDo("testing").toString());
    }

    @Test
    public void testSaveStringFormat() {
        assertEquals("T | 0 | testing", new ToDo("testing").saveStringFormat());
    }


}
