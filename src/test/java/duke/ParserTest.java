package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void ParserTest() {
        Parser parser = new Parser();
        parser.addCommand("command1", string -> {});
        parser.addCommand("command2", string -> {});
        parser.addCommand("command3", string -> {});

        assertDoesNotThrow(() ->
                parser.executeCommand("command1")
        );
        assertDoesNotThrow(() ->
                parser.executeCommand("command2")
        );

        assertThrows(DukeException.class, () ->
                parser.executeCommand("non-existent")
        );
    }
}
