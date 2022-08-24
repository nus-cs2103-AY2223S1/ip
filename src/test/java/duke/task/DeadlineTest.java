package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;

public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    public void setUp() {
        try {
            deadline = new Deadline("return book", Parser.strToDate("25 aug 6pm"));
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void getOtherData_returnCorrectTime() {
        assertEquals("2022-08-25T18:00", deadline.getOtherData());
    }

    @Test
    public void toString_returnString() {
        assertEquals("[D][ ] return book (by: Aug 25, 2022 6:00 PM)", deadline.toString());
    }
}
