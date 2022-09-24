package dan.parser;

import dan.task.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    public void parse_bye_getExit() {
        Parser parser = new Parser(new TaskList(new ArrayList<>()));
        parser.parse("bye");
        assertTrue(parser.getIsExit());
    }
}