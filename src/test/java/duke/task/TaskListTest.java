package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    @Test
    void containsTask_checkToDo() {
        tasks = new TaskList(null);
        tasks.add(new ToDo("task1"));
        assertTrue(tasks.containsTask(new ToDo("task1")));
        assertFalse(tasks.containsTask(new ToDo("task2")));
    }

    @Test
    void containsTask_checkDeadline() {
        tasks = new TaskList(null);
        Deadline d1 = new Deadline("deadline1", LocalDateTime.of(2020, 1, 1, 3, 0));
        Deadline d2 = new Deadline("deadline2", LocalDateTime.of(2020, 1, 1, 3, 0));
        Deadline d3 = new Deadline("deadline1", LocalDateTime.of(2020, 1, 1, 3, 1));
        tasks.add(d1);
        assertTrue(tasks.containsTask(d1));
        assertFalse(tasks.containsTask(d2));
        assertFalse(tasks.containsTask(d3));
    }

    @Test
    void containsTask_checkEvent() {
        tasks = new TaskList(null);
        Event e1 = new Event("event1", "some time");
        Event e2 = new Event("event2", "another time");

        tasks.add(e1);
        assertTrue(tasks.containsTask(e1));
        assertFalse(tasks.containsTask(e2));
    }

    @Test
    void testEquals() {
        TaskList tasks1 = new TaskList();
        TaskList tasks2 = new TaskList();
        TaskList tasks3 = new TaskList();
        ToDo task1 = new ToDo("task1");
        tasks1.add(task1);
        tasks2.add(task1);
        assertEquals(tasks1, tasks2);
        assertNotEquals(tasks1, tasks3);
    }
}
