package duke.model;

import duke.handlers.AddTodoCommand;
import duke.models.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseCommand_invalidInput_exceptionThrown(){
        try {
            Parser parser = new Parser();
            assertEquals(new AddTodoCommand(), parser.parseCommand("add todo a"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Command not found!", e.getMessage());
        }
    }
}
