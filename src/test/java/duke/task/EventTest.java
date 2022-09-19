package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.CorruptedLineException;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.util.ParsedData;

public class EventTest {
    @Test
    public void toStringTest() {
        try {
            Event dummy = Event.createEvent("dummy test123", "12-3-1232");
            assertEquals("[E][ ] dummy test123 (at: 12-3-1232)", dummy.toString());
            dummy.mark();
            assertEquals("[E][X] dummy test123 (at: 12-3-1232)", dummy.toString());
            dummy.unmark();
            assertEquals("[E][ ] dummy test123 (at: 12-3-1232)", dummy.toString());
        } catch (CorruptedLineException e) {
            fail();
        }
    }

    @Test
    public void markTest() {
        try {
            Event dummy = Event.createEvent("dummy test123", "12-3-1232");
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
        ParsedData data = new ParsedData("todo", "", "hello world 123");
        try {
            Event.createEvent(data);
            fail();
        } catch (EmptyDescriptionException e) {
            // pass test
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void emptyTimeExceptionTest() {
        ParsedData data = new ParsedData("todo", "sdfgdf", "");
        try {
            Event.createEvent(data);
            fail();
        } catch (EmptyTimeException e) {
            // pass test
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void convertToParseDataTest() {
        ParsedData data = new ParsedData("todo", "abc defg", "hello world 123");
        try {
            Event dummy = Event.createEvent(data);
            ParsedData result = dummy.convertToParseData();
            assertEquals("Ex", result.command);
            assertEquals("abc defg", result.description);
            assertEquals("hello world 123", result.additionalInfo);
            dummy.mark();
            result = dummy.convertToParseData();
            assertEquals("Ec", result.command);
            assertEquals("abc defg", result.description);
            assertEquals("hello world 123", result.additionalInfo);
        } catch (DukeException e) {
            fail();
        }
    }
}
