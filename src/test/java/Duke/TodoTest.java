package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo task = new Todo("drink", true);

    @Test
    public void getDoneTest() {
        assertEquals(1, task.getDone());
    }
    @Test
    public void getTaskTest() {
        assertEquals("drink", task.getTask());
    }
}