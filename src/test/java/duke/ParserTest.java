package duke;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseTest() {
        Parser p = new Parser(new Storage("./tests/test.txt"), new TaskList());
        String[] commands = {"bye"};
        assert p.parse(commands);
    }
}
