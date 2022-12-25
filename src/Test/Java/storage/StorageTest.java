package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Task;

public class StorageTest {
    StorageStub storage = new StorageStub();

    @Test
    public void testLoadTaskTodo() {
        String testInputString = "T|X|Bring clothes";
        try {
            Task task = storage.loadTask(testInputString);
            assertEquals(task.toString(), "[T][X] Bring clothes");
        } catch (DukeException de) {
            assertEquals(de.toString(), "Date time should be in the format yyyy-MM-dd HHmm!");
        }
    }

    @Test
    public void testLoadDeadline() {
        String testInputString = "D| |Return books|2022-09-14 1200";
        try {
            Task task = storage.loadTask(testInputString);
            assertEquals(task.toString(), "[D][ ] Return books (by: Wed, 14 Sep 2022, 12:00)");
        } catch (DukeException de) {
            assertEquals(de.toString(), "Date time should be in the format yyyy-MM-dd HHmm!");
        }
    }
}