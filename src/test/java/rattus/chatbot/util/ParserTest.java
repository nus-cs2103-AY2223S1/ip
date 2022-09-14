package rattus.chatbot.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rattus.chatbot.command.Command;
import rattus.chatbot.command.addcommands.AddToDoCommand;

public class ParserTest {
    @Test
    public void parseCommand_validToDo_commandEqual() {
        String userInput = "todo Walk the dog";
        Command actual = assertDoesNotThrow(() -> Parser.parseCommand(userInput));
        Command expected = new AddToDoCommand("Walk the dog");
        assertEquals(expected, actual);
    }
}
