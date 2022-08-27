package chatbot.parser;

import chatbot.exceptions.DukeException;
import chatbot.tasks.Deadline;
import chatbot.tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void test_parseIndex() {
        try {
            assertEquals(2, Parser.parseTargetIndex("2"));
        } catch (DukeException e) {
            fail();
        }

        try {
            Parser.parseTargetIndex("I hate CS");
            fail();
        } catch (DukeException e) {
           assertEquals(DukeException.TASK_INDEX_MISSING, e);
        }
    }

    @Test
    public void test_parseTimedTask() {
        try {
            String[] parsed = Parser.parseTimedTask(Deadline.TYPE, "Exchange application /by 2022-09-10");
            assertEquals("Exchange application", parsed[0]);
            assertEquals("2022-09-10", parsed[1]);
        } catch (DukeException e) {
            fail();
        }

        try {
            String[] parsed = Parser.parseTimedTask(Event.TYPE, "Welcome Tea /at 2022-08-10");
            assertEquals("Welcome Tea", parsed[0]);
            assertEquals("2022-08-10", parsed[1]);
        } catch (DukeException e) {
            fail();
        }

        try {
            String[] parsed = Parser.parseTimedTask(Deadline.TYPE, "Exchange application /at 2022-09-10");
            fail();
        } catch (DukeException e) {
            assertEquals(DukeException.INSUFFICIENT_TASK_SPECIFICATION, e);
        }

        try {
            String[] parsed = Parser.parseTimedTask(Event.TYPE, "Banquet");
            fail();
        } catch (DukeException e) {
            assertEquals(DukeException.INSUFFICIENT_TASK_SPECIFICATION, e);
        }
    }
}
