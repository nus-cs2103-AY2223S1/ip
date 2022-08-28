package duke.task;

import duke.exception.DukeException;
import duke.exception.TaskMarkException;
import duke.exception.TaskUnmarkException;
import duke.exception.UnexpectedDateTimeFormatException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskStub extends Task {
    private String description;
    private boolean isDone;

    public TaskStub(String description){
        super(description);
    }

    public TaskStub(String description, boolean done) {
        super(description,done);
    }

}

class TaskTest {

    @Test
    public void isDone_returnResult_success() {
        TaskStub taskUndone = new TaskStub("read book", false);
        TaskStub taskDone = new TaskStub("read book", true);
        assertEquals(false, taskUndone.isDone());
        assertEquals(true, taskDone.isDone());
    }

    @Test
    public void constructor_acceptableConstructor_success() {
        TaskStub task = new TaskStub("read book");
        TaskStub taskUndone = new TaskStub("read book", false);
        TaskStub taskDone = new TaskStub("read book", true);
        assertEquals("[ ] read book", task.toString());
        assertEquals("[ ] read book", taskUndone.toString());
        assertEquals("[X] read book", taskDone.toString());
    }

    @Test
    public void mark_undoneTaskToMark_success() throws TaskMarkException {
        TaskStub taskUndone = new TaskStub("read book", false);
        taskUndone.mark();
        assertEquals(true, taskUndone.isDone());
    }

    @Test
    public void unmark_doneTaskToMark_success() throws TaskUnmarkException {
        TaskStub taskDone = new TaskStub("read book", true);
        taskDone.unmark();
        assertEquals(false, taskDone.isDone());
    }

    @Test
    public void mark_doneTaskToMark_exceptionThrown() {
        try {
            TaskStub taskDone = new TaskStub("read book", true);
            taskDone.mark();
            fail();
        } catch (TaskMarkException e) {
            assertEquals("☹ OOPS!!! Task is already marked.", e.getMessage());
        }
    }

    @Test
    public void unmark_undoneTaskToMark_exceptionThrown() {
        try {
            TaskStub taskUndone = new TaskStub("read book", false);
            taskUndone.unmark();
            fail();
        } catch (TaskUnmarkException e) {
            assertEquals("☹ OOPS!!! Task has not been marked!", e.getMessage());
        }
    }


}