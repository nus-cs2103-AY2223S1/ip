package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void markAsDone_success() {
        Task task = new Task("return book");
        assertEquals(true, task.markAsDone());
    }
}
