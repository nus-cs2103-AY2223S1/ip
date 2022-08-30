package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TaskTest {
    @Test
    public void TaskTest() {
        Task task = Task.ToDo("task description");
        assertThrows(DukeException.class, () -> {
            task.changeMark(false);
        });
        assertDoesNotThrow(() -> {
            task.changeMark(true);
        });
        assertThrows(DukeException.class, () -> {
            task.changeMark(true);
        });

        assertDoesNotThrow(() -> {
            Task.parseFromString(Task.ToDo("task description").toString());
        });
        assertDoesNotThrow(() -> {
            Task.parseFromString(Task.Event("watch movie|2022-08-22").toString());
        });
        assertDoesNotThrow(() -> {
            Task.parseFromString(Task.Deadline("send homework|2022-08-23").toString());
        });
    }
}