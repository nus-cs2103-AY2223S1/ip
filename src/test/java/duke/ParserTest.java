package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_wrongCommand_exceptionThrown() {
        assertThrows(IllegalArgumentException.class,
                () -> Parser.parse("wrong command", new TaskList()));
        assertThrows(IllegalArgumentException.class,
                () -> Parser.parse("deadline aaa bbb /by 2022", new TaskList()));
        assertThrows(IllegalArgumentException.class,
                () -> Parser.parse("task ccc ddd /at 10/01/22", new TaskList()));
    }
}
