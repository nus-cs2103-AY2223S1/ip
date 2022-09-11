package Duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    @Test
    public void markAsDoneTest() {
        Task task = new Task("");
        task.markAsDone();
        boolean result = task.isDone;
        assertTrue(result);
    }
}
