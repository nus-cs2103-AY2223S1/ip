package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseWrongCommandTest() {
        assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("wrong command", new TaskList()));
        assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("deadline aaa bbb /by 2022", new TaskList()));
        assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("task ccc ddd /at 10/01/22", new TaskList()));
    }
}
