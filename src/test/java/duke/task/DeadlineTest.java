package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.constant.PriorityLevel;
import duke.exception.DukeException;

public class    DeadlineTest {
    @Test
    public void createDeadline_noMarkSpecified_unmarkedDeadlineReturned() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02");
            assertEquals("[D][ ] Test (by: Feb 2 2022)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void createDeadline_deadlineUnmarked_unmarkedDeadlineReturned() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02", false, PriorityLevel.NONE);
            assertEquals("[D][ ] Test (by: Feb 2 2022)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void createDeadline_deadlineMarked_markedDeadlineReturned() {
        try {
            Deadline deadline = new Deadline("Test", "2023-03-03", true, PriorityLevel.NONE);
            assertEquals("[D][X] Test (by: Mar 3 2023)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toSaveFileString_deadlineUnmarked_stringRepresentationMatch() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02", false, PriorityLevel.NONE);
            assertEquals("[D] @ [ ] @ none @ Test @ 2022-02-02", deadline.toSaveFileString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toSaveFileString_deadlineMarked_stringRepresentationMatch() {
        try {
            Deadline deadline = new Deadline("Test", "2023-03-03", true, PriorityLevel.NONE);
            assertEquals("[D] @ [X] @ none @ Test @ 2023-03-03", deadline.toSaveFileString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markAsDone() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02", false, PriorityLevel.NONE);
            deadline.markAsDone();
            assertEquals("[D][X] Test (by: Feb 2 2022)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markAsUndoneTest() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02", true, PriorityLevel.NONE);
            deadline.markAsUndone();
            assertEquals("[D][ ] Test (by: Feb 2 2022)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
