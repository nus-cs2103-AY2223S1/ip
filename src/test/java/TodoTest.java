import jarvis.task.Deadline;
import jarvis.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void markUndoneTest() {
        Todo todo = new Todo("toodoo", true);
        assertEquals("[T][X] toodoo", todo.toString());
    }
}
