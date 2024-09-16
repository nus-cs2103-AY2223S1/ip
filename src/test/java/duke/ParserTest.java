package duke;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.InvalidCommandException;
import duke.task.DeadlineTask;
import duke.task.EventTask;

public class ParserTest {

    @Test 
    public void parse_event_eventTask() {
        // a unit test to parse commands for new event.
        Command expected = new AddCommand(new EventTask(
                "xyz", LocalDateTime.of(1111,11,11,11,11), LocalDateTime.of(1212,12,12,12,12)));
        try {
            Command actual = Parser.parse("event xyz /from 11.11.1111 11:11 /to 12.12.1212 12:12");
            assertEquals(expected, actual);
        } catch (InvalidCommandException e) {
            throw new AssertionError();
        }
    }

    @Test 
    public void parse_deadline_deadlineTask() {
        // a unit test to parse commands for new deadline.
        Command expected = new AddCommand(
                new DeadlineTask("xyz", LocalDateTime.of(1111,11,11,11,11)));
        try {
            Command actual = Parser.parse("deadline xyz /by 11.11.1111 11:11");
            assertEquals(expected, actual);
        } catch (InvalidCommandException e) {
            throw new AssertionError();
        }
    }
}
