package duke.chatbot.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.chatbot.command.AddTaskCommands.AddToDoCommand;
import org.junit.jupiter.api.Test;

import duke.chatbot.command.Command;
import duke.chatbot.data.task.TaskList;

public class ParserTest {
    @Test
    public void parseCommand_validToDo_commandEqual() {
        String userInput = "todo Walk the dog";
        Command actual = assertDoesNotThrow(() -> Parser.parseCommand(userInput));
        Command expected = new AddToDoCommand("Walk the dog");
        assertEquals(expected, actual);
    }
}
