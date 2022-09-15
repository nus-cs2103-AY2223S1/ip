package Duke;

import Duke.Exceptions.DukeException;
import Duke.Parser.GUIParser;
import Duke.Tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GUIParserTest {
    @Test
    public void testGUIParser() throws DukeException {
        GUIParser guiParser = new GUIParser();
        String actual = guiParser.parseCommand("bye", new TaskList()).execute();
        String expected = "Bye ~ Hope you have enjoy your time ~~";

        assertEquals(expected, actual);
    }


}