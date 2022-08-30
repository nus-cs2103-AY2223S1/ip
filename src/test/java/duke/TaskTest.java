package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TaskTest {
    @Test
    public void TaskTest() {
        Task task = Task.toDo("task description");
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
            Task.parseFromString(Task.toDo("task description").toString());
        });
        assertDoesNotThrow(() -> {
            Task.parseFromString(Task.event("watch movie|2022-08-22").toString());
        });
        assertDoesNotThrow(() -> {
            Task.parseFromString(Task.deadline("send homework|2022-08-23").toString());
        });
    }
}