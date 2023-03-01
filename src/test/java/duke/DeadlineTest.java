package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void incorrectDateTest() {
        Throwable dukeExceptionThrown = assertThrows(DukeException.class, () -> {
            Deadline deadline = new Deadline("Buy bread.", "20 July 2016");
        });
        assertEquals(dukeExceptionThrown.getMessage(), "Please use format yyyy-mm-dd for your deadline!");
    }

    @Test
    public void createTest() {
        try {
            Deadline deadline = new Deadline("Buy bread", "2000-09-09");
            assertEquals(deadline.toString(), "  [D] [ ] Buy bread (by: 2000-09-09)");
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
