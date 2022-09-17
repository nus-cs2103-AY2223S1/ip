package duke.task;

import org.junit.jupiter.api.Test;
import duke.exceptions.CorruptedLineException;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.util.ParsedData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        try {
            Deadline dummy = Deadline.createDeadline("dummy test123", "12-3-1232");
            assertEquals("[D][ ] dummy test123 (by: 12-3-1232)", dummy.toString());
            dummy.mark();
            assertEquals("[D][X] dummy test123 (by: 12-3-1232)", dummy.toString());
            dummy.unmark();
            assertEquals("[D][ ] dummy test123 (by: 12-3-1232)", dummy.toString());
        } catch (CorruptedLineException e) {
            fail();
        }
    }

    @Test
    public void markTest() {
        try {
            Deadline dummy = Deadline.createDeadline("dummy test123", "12-3-1232");
            assertEquals(false, dummy.isComplete);
            dummy.mark();
            assertEquals(true, dummy.isComplete);
            dummy.mark();
            assertEquals(true, dummy.isComplete);
            dummy.unmark();
            assertEquals(false, dummy.isComplete);
            dummy.unmark();
            assertEquals(false, dummy.isComplete);
        } catch (CorruptedLineException e) {
            fail();
        }
    }

    @Test
    public void emptyDescriptionExceptionTest() {
        ParsedData data = new ParsedData("deadline", "", "hello world 123");
        try {
            Deadline.createDeadline(data);
            fail();
        } catch (EmptyDescriptionException e) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void emptyTimeExceptionTest() {
        ParsedData data = new ParsedData("deadline", "sdfgdf", "");
        try {
            Deadline.createDeadline(data);
            fail();
        } catch (EmptyTimeException e) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void convertToParseDataTest() {
        ParsedData data = new ParsedData("todo", "abc defg", "hello world 123");
        try {
            Deadline dummy = Deadline.createDeadline(data);
            ParsedData result = dummy.convertToParseData();
            assertEquals("Dx", result.command);
            assertEquals("abc defg", result.description);
            assertEquals("hello world 123", result.additionalInfo);
            dummy.mark();
            result = dummy.convertToParseData();
            assertEquals("Dc", result.command);
            assertEquals("abc defg", result.description);
            assertEquals("hello world 123", result.additionalInfo);
        } catch (DukeException e) {
            fail();
        }
    }
}
