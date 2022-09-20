package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markAsDone_success() {
        Task task = new Task("return book");
        assertEquals(true, task.markAsDone());
    }
}
