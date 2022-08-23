package henry;

import command.Command;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Parser parser = new Parser();
    TaskListStub taskList = new TaskListStub(new ArrayList<>());
    String command = "event event stub /at 01-01-2000 00:00";
    String desired = "OK. I ADDED THIS TASK TO MY LIST:\n\t\t\t [E][ ] event stub (at: 01-01-2000 00:00).";

    @Test
    public void parse_ErrorCommand_success() {
        Command c = parser.parseCommand(command);
        c.setData(taskList);
        assertEquals(c.execute().feedback, desired);
    }
}
