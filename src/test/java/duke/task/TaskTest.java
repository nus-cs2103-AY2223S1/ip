package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void checkContainsKeyword_validInput_returnAsExpected() {
        assertTrue(sampleTask.containsKeyword("sam"));
        assertTrue(sampleTask.containsKeyword("PLE"));
        assertFalse(sampleTask.containsKeyword("sample."));
    }

    @Test
    public void checkContainsAnyTags_validInput_returnAsExpected() {
        assertTrue(sampleTask.containsAnyTags("tag1"));
        assertTrue(sampleTask.containsAnyTags("tag2", "tag3"));
        assertFalse(sampleTask.containsAnyTags("tag12"));
    }

    @Test
    public void checkContainsAllTags_validInput_returnAsExpected() {
        assertTrue(sampleTask.containsAllTags("tag1", "tag2"));
        assertFalse(sampleTask.containsAllTags("tag1", "tag2", "tag3"));
        assertFalse(sampleTask.containsAllTags("tag1", "tag3"));
    }
}
