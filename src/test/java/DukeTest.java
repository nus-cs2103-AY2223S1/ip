import duke.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void DeadlineTest() {
        Deadline test = new Deadline("test_item", "2000-01-01");
        assertEquals(test.toString(), "[D][ ] test_item by: Jan 1 2000");
        assertEquals(test.isCompleted(), false);
        test.completeToggle();
        assertEquals(test.toString(), "[D][X] test_item by: Jan 1 2000");
        assertEquals(test.isCompleted(), true);
    }

    @Test
    public void EventTest() {
        Event test = new Event("test_item", "2000-01-01");
        assertEquals(test.toString(), "[E][ ] test_item at: Jan 1 2000");
        assertEquals(test.isCompleted(), false);
        test.completeToggle();
        assertEquals(test.toString(), "[E][X] test_item at: Jan 1 2000");
        assertEquals(test.isCompleted(), true);
    }

    @Test
    public void TodoTest() {
        Todo test = new Todo("test_item");
        assertEquals(test.toString(), "[T][ ] test_item");
        assertEquals(test.isCompleted(), false);
        test.completeToggle();
        assertEquals(test.toString(), "[T][X] test_item");
        assertEquals(test.isCompleted(), true);
    }
}
