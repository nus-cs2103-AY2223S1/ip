package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void createEventTest() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02");
            assertEquals("[D][ ] Test (by: Feb 2 2022)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void createEventTest2() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02", false);
            assertEquals("[D][ ] Test (by: Feb 2 2022)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void createEventTest3() {
        try {
            Deadline deadline = new Deadline("Test", "2023-03-03", true);
            assertEquals("[D][X] Test (by: Mar 3 2023)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void saveFileStringTest() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02", false);
            assertEquals("[D] @ [ ] @ Test @ 2022-02-02", deadline.toSaveFileString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void saveFileStringTest2() {
        try {
            Deadline deadline = new Deadline("Test", "2023-03-03", true);
            assertEquals("[D] @ [X] @ Test @ 2023-03-03", deadline.toSaveFileString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markAsDoneTest() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02", false);
            deadline.markAsDone();
            assertEquals("[D][X] Test (by: Feb 2 2022)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markAsUndoneTest() {
        try {
            Deadline deadline = new Deadline("Test", "2022-02-02", true);
            deadline.markAsUndone();
            assertEquals("[D][ ] Test (by: Feb 2 2022)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
