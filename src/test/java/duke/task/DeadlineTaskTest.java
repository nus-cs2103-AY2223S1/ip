package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.ChickException;
import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    public void noSeparator_exceptionThrown() {
        try {
            DeadlineTask task = new DeadlineTask("deadline homework 2022-08-29");
        } catch (ChickException e) {
            assertEquals(e.getMessage(), "Deadline time (indicated by /by separator) is missing.");
        }
    }

    @Test
    public void multipleSeparator_exceptionThrown() {
        try {
            DeadlineTask task = new DeadlineTask("deadline homework /by 2022-08-29 /by 2359");
        } catch (ChickException e) {
            assertEquals(e.getMessage(), "Multiple usage of /by separator is not allowed.");
        }
    }
}
