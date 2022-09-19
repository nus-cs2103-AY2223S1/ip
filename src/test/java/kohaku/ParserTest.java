package kohaku;


import kohaku.datastruct.TaskList;
import kohaku.daveexceptions.DaveException;
import kohaku.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    /**
     * Tests for the parsing of invalid commands.
     */
    @Test
    public void dispatch_invalidInput_throwsDaveException() {
        try {
            TaskList tasks = new TaskList();
            Parser.dispatch("invalid command", "",tasks);
            fail();
        } catch (DaveException e) {
            assertEquals("I'm sowwy, but I don't know what that means! ;~;", e.getMessage());
        }
    }

    /**
     * Tests for the parsing of task inputs with invalid datetime.
     */
    @Test
    public void parseTask_invalidDatetime_throwsDaveException() {
        try {
            Parser.parseTask("task /at 19-09-22 16:00");
            fail();
        } catch (DaveException e) {
            assertEquals("Please input a valid date! >~<", e.getMessage());
        }
    }
}
