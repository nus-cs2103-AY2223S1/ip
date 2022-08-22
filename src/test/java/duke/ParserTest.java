package duke;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void splitArgument_nonEmptyString_success() throws Exception {
        assertArrayEquals(
                new String[]{"assignment 1", "19-02-2020"},
                Parser.splitArgument("assignment 1 /by 19-02-2020", "/by"));
        assertArrayEquals(
                new String[]{"exam for CS2103T", "20 Nov 2022"},
                Parser.splitArgument("exam for CS2103T /at 20 Nov 2022", "/at"));
        assertArrayEquals(
                new String[]{"Finish iP", "2022-02-02"},
                Parser.splitArgument("Finish iP /by 2022-02-02", "/by"));
    }

    @Test
    public void splitArgument_emptyString_exceptionThrown() throws Exception {
        try {
            assertArrayEquals(
                    new String[]{"", ""},
                    Parser.splitArgument("", "/at"));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("Oops! /at not found.", e.getMessage());
        }
    }
}
