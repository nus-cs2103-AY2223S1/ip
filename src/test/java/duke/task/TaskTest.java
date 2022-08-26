package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TaskTest {
    @Test
    public void parseData_todo_success() throws DukeException {
        Task test = Task.parseData("T |   | test");
        assertEquals("[T][ ] test", test.toString());
    }

    @Test
    public void parseData_todoNoDescription_exceptionThrown() {
        try {
            Task.parseData("T |   | ");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task data loaded.", e.getMessage());
        }
    }

    @Test
    public void parseData_deadline_success() throws DukeException {
        Task test = Task.parseData("D | X | test | 2022-10-12");
        assertEquals("[D][X] test (by: Oct 12 2022)", test.toString());
    }

    @Test
    public void parseData_deadlineNoBy_exceptionThrown() {
        try {
            Task test = Task.parseData("D | X | test | ");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task data loaded.", e.getMessage());
        }
    }

    @Test
    public void parseData_event_success() throws DukeException {
        Task test = Task.parseData("E |   | test | 2022-10-12");
        assertEquals("[E][ ] test (at: Oct 12 2022)", test.toString());
    }

    @Test
    public void parseData_eventNoAt_exceptionThrown() {
        try {
            Task test = Task.parseData("E |   | test | ");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task data loaded.", e.getMessage());
        }
    }

    @Test
    public void parseData_invalidTaskType_exceptionThrown() {
        try {
            Task.parseData("X | X | doesn't matter");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task data loaded.", e.getMessage());
        }
    }

    @Test
    public void parseData_invalidDoneStatus_exceptionThrown() {
        try {
            Task.parseData("T | P | doesn't matter");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task data loaded.", e.getMessage());
        }
    }
}
