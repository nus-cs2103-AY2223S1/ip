package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private final Task sampleTask = new TaskStub();

    @Test
    public void markTask_getCorrectStatusIcon() {
        sampleTask.markAsFinished();
        assertEquals(sampleTask.getStatusIcon(), "X");
    }

    @Test
    public void unMarkTask_getCorrectStatusIcon() {
        sampleTask.markAsNotFinished();
        assertEquals(sampleTask.getStatusIcon(), " ");
    }
}
