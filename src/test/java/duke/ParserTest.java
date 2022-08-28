package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parserTest() {
        Parser parser = new Parser(new TaskList(new ArrayList<>()), new Storage(""));

        String temp = "deadline homework due /at 24/04/2022";

        try {
            parser.parse(temp);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-("
                    + "\nCommands should be in the following format:"
                    + "\n\tlist"
                    + "\n\ttodo (description)"
                    + "\n\tdeadline (description) /by (dd/MM/yyyy)"
                    + "\n\tevent (description) /at (time)"
                    + "\n\tmark (number)"
                    + "\n\tunmark (number)"
                    + "\n\tdelete (number)", e.getMessage());
        }
    }

}
