package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.MarkCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnmarkCommand;


class ParserTest {
    @Test
    void processDeadline() {
        String[] invalidInputs = {"deadline no deadline", "deadline name /by invalid date"};
        String validInput = "deadline name /by 01/01/2020 03:00";
        try {
            for (String input : invalidInputs) {
                Command command = Parser.parse(input);
                assertTrue(command instanceof InvalidCommand);
            }
            Command command = Parser.parse(validInput);
            assertTrue(command instanceof DeadlineCommand);
            assertEquals(new DeadlineCommand("name",
                            LocalDateTime.of(2020, 1, 1, 3, 0)),
                    command);

        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    void processDelete() {
        String[] invalidInputs = {"deadline no deadline", "deadline name /by invalid date"};
        String validInput = "deadline name /by 01/01/2020 03:00";
        try {
            for (String input : invalidInputs) {
                Command command = Parser.parse(input);
                assertTrue(command instanceof InvalidCommand);
            }
            Command command = Parser.parse(validInput);
            assertTrue(command instanceof DeadlineCommand);
            assertEquals(new DeadlineCommand("name",
                            LocalDateTime.of(2020, 1, 1, 3, 0)),
                    command);

        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    void getNumberRegex() {
        String[] validInputs = {"1", "123", "1234567890"};
        String[] invalidInputs = {"1x", "-123", "a"};
        for (String input : validInputs) {
            Matcher matcher = Pattern.compile(Parser.getNumberRegex("test")).matcher(input);
            assertTrue(matcher.matches() && matcher.group("test").equals(input.trim()));
        }
        for (String input : invalidInputs) {
            Matcher matcher = Pattern.compile(Parser.getNumberRegex("test")).matcher(input);
            assertFalse(matcher.matches() && matcher.group("test").equals(input.trim()));
        }
    }

    @Test
    void getTextRegex() {
        String[] validInputs = {"x y z", "-123"};
        String[] invalidInputs = {"asdf/y"}; // invalid because of /
        for (String input : validInputs) {
            Matcher matcher = Pattern.compile(Parser.getTextRegex("test")).matcher(input);
            assertTrue(matcher.matches() && matcher.group("test").equals(input.trim()));
        }
        for (String input : invalidInputs) {
            Matcher matcher = Pattern.compile(Parser.getTextRegex("test")).matcher(input);
            assertFalse(matcher.matches() && matcher.group("test").equals(input.trim()));
        }
    }

    @Test
    void getDateTimeRegex() {
        String[] validInputs = {"12/12/2000 10:50", "01/10/1010 23:59"};
        String[] invalidInputs = {"1/1/2999 1:10", "10 dec 2020"};
        for (String input : validInputs) {
            Matcher matcher = Pattern.compile(Parser.getDateTimeRegex("test")).matcher(input);
            assertTrue(matcher.matches() && matcher.group("test").equals(input.trim()));
        }
        for (String input : invalidInputs) {
            Matcher matcher = Pattern.compile(Parser.getDateTimeRegex("test")).matcher(input);
            assertFalse(matcher.matches() && matcher.group("test").equals(input.trim()));
        }
    }

    @Test
    void combineRegexes() {
        assertEquals(Parser.combineRegexes("a", "b", "c"), "a\\s+b\\s+c");
    }

    @Test
    void testParse() {
        HashMap<String, Command> testCases = new HashMap<>();
        testCases.put("todo test", new ToDoCommand("test"));
        testCases.put("event test /at description", new EventCommand("test", "description"));
        testCases.put("event test", new InvalidCommand("Invalid event format"));
        testCases.put("find  some description", new FindCommand("some description"));
        testCases.put("deadline thing /by  22/10/2013 13:30",
                new DeadlineCommand("thing", LocalDateTime.of(2013, 10, 22, 13, 30)));
        testCases.put("mark 1", new MarkCommand(1));
        testCases.put("unmark 1", new UnmarkCommand(1));
        testCases.put("delete 0", new DeleteCommand(0));
        testCases.put("bye ", new ExitCommand());
        for (String input : testCases.keySet()) {
            try {
                assertEquals(testCases.get(input), Parser.parse(input));
            } catch (DukeException e) {
                Assertions.fail();
            }
        }
    }
}
