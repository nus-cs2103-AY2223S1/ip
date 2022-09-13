import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.ClientList;
import duke.Storage;
import duke.command.CommandOutputs;
import duke.task.TaskList;
import duke.task.ToDos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListTaskCommand;

public class ParserTest {
    @Test
    @DisplayName("Check parse input for bye and list")
    public void checkParseInputByeAndList() {
        Command c1 = null;
        Command c2 = null;
        try {
            c1 = Parser.parseInput("bye");
            c2 = Parser.parseInput("list");

        } catch (DukeException e) {
            fail(e.getMessage());
        }
        assertEquals(ExitCommand.of(), c1);
        assertEquals(ListTaskCommand.of(), c2);
        try {
            ClientList clientList = new ClientList();
            TaskList taskList = new TaskList();
            Command toDo = Parser.parseInput("todo read book");
            String output = toDo.execute(taskList, clientList);
            assertEquals(output, CommandOutputs.showAdd(taskList, new ToDos("read book", false)));;
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
