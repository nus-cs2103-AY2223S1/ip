package duke.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.DukeException;

class TaskListTest {
    private TaskList tasks;
    @BeforeEach
    void setUp() {
        tasks = new TaskList(null);
        tasks.add(new ToDo("task1"));
        tasks.add(new Deadline("task2", LocalDateTime.of(2020, 1, 1, 3, 0)));
    }

    @Test
    void setCompletion_validInputs() {
        try {
            tasks.setCompletion(0, true);
            assertTrue(tasks.get(0).isDone);
            tasks.setCompletion(0, false);
            assertFalse(tasks.get(0).isDone);
        } catch (DukeException e) {
            fail();
        }
    }
    @Test
    void setCompletion_invalidIndex_exceptionThrown() {
        assertThrows(DukeException.class, () -> tasks.setCompletion(5, true));
    }
}
