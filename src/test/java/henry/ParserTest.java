package henry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import command.Command;

public class ParserTest {

    @Test
    public void parseCommand_AddedTask_IfParserIsWorking() {
        Parser parser = new Parser();
        TaskListStub taskList = new TaskListStub(new ArrayList<>());
        String command = "event event stub /at 01-01-2000 00:00";
        String desired = "OK. I ADDED THIS TASK TO MY LIST:\n\t\t\t [E][ ] event stub (at: 01-01-2000 00:00).";
        Command c = parser.parseCommand(command);
        c.setData(taskList);
        assertEquals(c.execute().toString(), desired);
    }
}
