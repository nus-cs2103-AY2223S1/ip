package henry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import command.Command;
import stubs.TaskListStub;

public class ParserTest {

    @Test
    public void testParser() {
        Parser parser = new Parser();
        TaskListStub taskList = new TaskListStub(new ArrayList<>());
        String command = "event event stub /at 12-12-2050 00:00";
        String desired = "OK. I ADDED THIS TASK TO MY LIST:\n [E][ ] event stub (at: 12-12-2050 12:00 AM)";
        Command c = parser.parseCommand(command);
        c.setData(taskList);
        assertEquals(desired, c.execute().toString());
    }
}
