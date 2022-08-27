import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Task;

public class TaskTest {
    @Test
    public void testEmptyDescription() {
        Task t = new Task("", false);
        assertEquals("", t.getDescription());
    }

    @Test
    public void testChangeIsDone() {
        Task t = new Task("test", false);

        t.changeIsDone(true);
        assertEquals(true, t.getIsDone());

        t.changeIsDone(false);
        assertEquals(false, t.getIsDone());
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

        t.setDescription("Eat food");

        assertEquals("[ ] Eat food", t.toString());

        t.changeIsDone(true);

        assertEquals("[X] Eat food", t.toString());
    }
}
