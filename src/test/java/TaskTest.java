import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testEmptyDescription() {
        Task t = new Task("", false);
        assertEquals("", t.description);
    }

    @Test
    public void testChangeIsDone() {
        Task t = new Task("test", false);

        t.changeIsDone(true);
        assertEquals(true, t.isDone);

        t.changeIsDone(false);
        assertEquals(false, t.isDone);
    }

    @Test
    public void testSuccessfulCanChangeIsDone() {
        Task t = new Task("test", false);

        assertEquals(true, t.canChangeIsDone(true));

        assertEquals(false, t.canChangeIsDone(false));

        t.changeIsDone(true);

        assertEquals(false, t.canChangeIsDone(true));

        assertEquals(true, t.canChangeIsDone(false));
    }

    @Test
    public void testToString() {
        Task t = new Task("", false);

        assertEquals("[ ] ", t.toString());

        t.changeIsDone(true);

        assertEquals("[X] ", t.toString());

        t.changeIsDone(false);

        assertEquals("[ ] ", t.toString());

        t.description = "Eat food";

        assertEquals("[ ] Eat food", t.toString());

        t.changeIsDone(true);

        assertEquals("[X] Eat food", t.toString());
    }
}
