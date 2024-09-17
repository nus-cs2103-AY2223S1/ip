package dan.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dan.task.TaskList;

class ParserTest {

    @Test
    public void parse_bye_getExit() {
        Parser parser = new Parser(new TaskList(new ArrayList<>()));
        parser.parse("bye");
        assertTrue(parser.getIsExit());
    }
}
