package ekud.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void markTask_todo_correctStatusIcon() {
        Task task = new ToDo("Test description");
        assertEquals(task.getStatusIcon(), " ");
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "X");
    }
}
