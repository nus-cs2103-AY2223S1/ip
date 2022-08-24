package duke;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.TaskList;
import duke.CommandType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void getCommandType_incorrectInput_nonsenseRecognized() {
        TaskList emptyTaskList = new TaskList();
        Parser parser = new Parser(emptyTaskList);
        assertEquals(parser.getCommandType("bruospdk"), CommandType.NONSENSE);
    }

    @Test
    public void execute_validEventAddition_eventIsAdded() {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        parser.execute("event New Year's Day 2023 @ 2023-01-01T00:00:00");
        assertEquals(taskList.size(), 1);
    }
}
