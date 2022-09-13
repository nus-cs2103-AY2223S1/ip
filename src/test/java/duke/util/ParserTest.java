package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.FileParseException;
import duke.exception.NoArgumentException;
import duke.exception.WrongArgumentException;

public class ParserTest {
    private Parser p;

    @Test
    public void todoNoArgument() {
        this.p = new Parser(new TaskList());
        try {
            p.parseInput("todo", false);
        } catch (NoArgumentException | WrongArgumentException | FileParseException e) {
            if (e instanceof NoArgumentException) {
                assertEquals("The proper command is: todo [description]", (
                        (NoArgumentException) e).getMessage());
            }
        }
    }

    @Test
    public void todoValid() throws NoArgumentException, WrongArgumentException, FileParseException {

    }
}
