package duke.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
