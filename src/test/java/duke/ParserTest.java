package duke;

import command.Command;
import command.TodoCommand;
import org.junit.jupiter.api.Test;
import task.Event;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void byeParserTest() {
        assertDoesNotThrow(() -> {
            String str = "bye";
            Command command = Parser.parse(str);
            assertTrue(command.isExit());
                }
        );
    }
}
