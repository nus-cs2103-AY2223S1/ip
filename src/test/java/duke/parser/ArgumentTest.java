package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ArgumentTest {

    @Test
    public void getName() {
        Argument arg = new Argument("byu", "random parameter content");
        assertEquals("byu", arg.getName());
    }

    @Test
    public void getBody() {
        Argument arg = new Argument("byu", "arbitrary argument body");
        assertEquals("arbitrary argument body", arg.getBody());
    }
}
