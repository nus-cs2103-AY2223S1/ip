package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void markAsDoneTaskTickShown() {
        Task task1 = new Todo("Sample Task");
        task1.markAsDone();
        assertEquals("[T][✓] Sample Task", task1.toString());
    }

    @Test
    public void markAsNotDoneTaskTickRemoved() {
        Task task1 = new Todo("Sample Task");
        task1.markAsDone();
        task1.markAsNotDone();
        assertEquals("[T][ ] Sample Task", task1.toString());
    }
}
