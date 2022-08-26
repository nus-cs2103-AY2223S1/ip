package Duke;
import Duke.Parser;
import Duke.Command.AddCommand;
import Duke.Command.Command;
import Duke.Command.InvalidCommandException;
import Duke.Task.DeadlineTask;
import Duke.Task.EventTask;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test 
    public void parseTestEvent() {
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
    public void parseTestDeadline() {
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
