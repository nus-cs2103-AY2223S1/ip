package dan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    public void parse_bye_getExit() {
        Parser parser = new Parser(new TaskList(new ArrayList<>()));
        assertTrue(parser.parse("bye"));
    }
}