package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


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
                assertEquals("The proper command is: \ntodo [description]", e.getMessage());
            }
        }
    }

    @Test
    public void unknownCommand() {
        String check = "";
        this.p = new Parser(new TaskList());
        try {
            check = p.parseInput("whatever", false);
        } catch (WrongArgumentException | FileParseException | NoArgumentException | ClassCastException e) {
            fail();
        }
        assertEquals(check, "what's this?! REDO!!!!");
    }
}
