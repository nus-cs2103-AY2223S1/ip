package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DeadlineException;

public class DeadlineTest {
    @Test
    public void createDeadline_validInput_getCorrectStringRepresentation() {
        try {
            Deadline sampleDeadline = new Deadline("cs1101s grading",
                    "2022-08-23");
            assertEquals(sampleDeadline.toString(), "[D][ ] cs1101s grading "
                    + "(by: Aug 23 2022)");
        } catch (DeadlineException error) {
            fail("Should not have thrown any exception");
        }

    }

    @Test
    public void createDeadline_validInput_getCorrectStorageRepresentation() {
        try {
            Deadline sampleDeadline = new Deadline("cs2100 quiz",
                    "2022-09-10");
            assertEquals(sampleDeadline.toStorageRepresentation(), "D| |"
                    + "cs2100 quiz|2022-09-10");
        } catch (DeadlineException error) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void createDeadline_invalidInput_throwError() {
        try {
            Deadline sampleDeadline = new Deadline("cs2102 hw", "next month");
            fail("Should have thrown DeadlineException");
        } catch (DeadlineException error) {
            return;
        }
    }
}
