package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

public class TaskListTest {
    private static class TaskStub extends Task {
        public TaskStub() {
            super("", false);
        }

        @Override
        public String getTaskType() {
            return "TaskStub";
        }

        @Override
        public Optional<LocalDateTime> getTime() {
            return Optional.empty();
        }

        @Override
        public String toString() {
            return "taskstub";
        }
    }

    @Test
    public void addTaskTest() {
        Task task = new TaskStub();
        TaskList taskList = new TaskList();
        taskList.addTask(task);
        assertEquals(taskList.getTask(0), task);
    }

    @Test
    public void deleteTaskTest() {
        IndexOutOfBoundsException thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            TaskList taskList = new TaskList();
            taskList.deleteTask(0);
        });

        assertEquals("Index 0 out of bounds for length 0", thrown.getMessage());
    }
}
