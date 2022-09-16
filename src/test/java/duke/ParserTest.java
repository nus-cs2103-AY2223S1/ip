package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;


public class ParserTest {

    @Test
    public void write_invalidInput_ErrorMessage() {
        Parser testParse = new Parser();
        Storage testStorage = new Storage();
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(new ToDo("return book"));
        Command command = testParse.parse("mark 3");
        String response = command.execute(testTaskList, testStorage);
        assertEquals(response, "Hmm, I can't find this hole number, enter a valid integer number");
    }

}
