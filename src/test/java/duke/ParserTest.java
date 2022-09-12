package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseValidDeadlineTest() {
        try {
            Parser.parseInput("deadline Valid Deadline /by 2022-08-21");
            assertTrue(true);
        } catch (DukeException e) {
            assertEquals("Supposed to be a valid deadline", "Exception thrown");
        }
    }

    @Test
    public void parseInvalidDeadlineTest1() {
        try {
            Parser.parseInput("deadline /by 2022-08-21");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty :-(", e.getMessage());
        }
    }

    @Test
    public void parseInvalidDeadlineTest2() {
        try {
            Parser.parseInput("deadline Invalid Deadline /by 2022");
            fail();
        } catch (DukeException e) {
            assertEquals("Please format date in YYYY-MM-DD.", e.getMessage());
        }
    }

    @Test
    public void parseInvalidDeadlineTest3() {
        try {
            Parser.parseInput("deadline Invalid Deadline 2022");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
