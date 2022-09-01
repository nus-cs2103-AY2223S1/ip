package duke.util;

import duke.exception.FileParseException;
import duke.exception.NoArgumentException;
import duke.exception.WrongArgumentException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {
    Parser p = new Parser(new TaskList());

    @Test
    public void todoNoArgument() {
        try {
            p.parseInput("todo", false);
        } catch (NoArgumentException | WrongArgumentException | FileParseException e) {
            if (e instanceof NoArgumentException) {
                assertEquals("The proper command is: todo [description]",
                        ((NoArgumentException) e).getMessage());
            }
        }
    }

    @Test
    public void todoValid() throws NoArgumentException, WrongArgumentException, FileParseException {

    }
}
