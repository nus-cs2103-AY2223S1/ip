package rick;

import org.junit.jupiter.api.Test;
import rick.tasks.Event;
import rick.tasks.Task;
import rick.tasks.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void constructor_invalidTask_throwsException() throws NullPointerException {
        assertThrows(NullPointerException.class, () -> new Event(null));
    }

    @Test
    public void constructor_validTask_success() {
        assertDoesNotThrow(() -> new Todo("read book"));
    }

    @Test
    public void markAsDone_success() throws RickException {
        Task task = new Todo("test");
        assertEquals(" ", task.getStatusIcon());
        task.setDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void markAsUndone_success() throws RickException {
        Task task = new Todo("test");
        assertEquals(" ", task.getStatusIcon());
        task.setDone();
        assertEquals("X", task.getStatusIcon());
        task.setUndone();
        assertEquals(" ", task.getStatusIcon());
    }
}
