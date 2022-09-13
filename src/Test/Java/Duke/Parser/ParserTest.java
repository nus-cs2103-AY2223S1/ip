package Duke.Parser;

import org.junit.jupiter.api.Test;
import Duke.Exception.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    ParserStub parserStub = new ParserStub();

    @Test
    public void testTokenizeTodo() {
        String inputTestString = "borrow book";
        try {
            String[] actualOutput = parserStub.tokenize(inputTestString, "/");
            assertEquals(actualOutput.length, 1);
            assertEquals(actualOutput[0], "borrow book");
        } catch (DukeException de) {
            assertEquals(de.toString(), "No input");
        }

    }

    @Test
    public void testTokenizeDeadline() {
        try {
            String inputTestString = "return book /by 2022-09-14 1200";
            String[] actualOutput = parserStub.tokenize(inputTestString, "/by");
            assertEquals(actualOutput.length, 2);
            assertEquals(actualOutput[0], "return book ");
            assertEquals(actualOutput[1], " 2022-09-14 1200");
        } catch (DukeException de) {
            assertEquals(de.toString(), "No input");
        }
    }

    @Test
    public void testTokenizeEvent() {
        try {
            String inputTestString = "return book /at 2022-09-14 1200";
            String[] actualOutput = parserStub.tokenize(inputTestString, "/at");
            assertEquals(actualOutput.length, 2);
            assertEquals(actualOutput[0], "return book ");
            assertEquals(actualOutput[1], " 2022-09-14 1200");
        } catch (DukeException de) {
            assertEquals(de.toString(), "No input");
        }
    }
}