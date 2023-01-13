package kirby;

import static kirby.Parser.getArgument;
import static kirby.Parser.getArguments;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getArguments_events() {
        String fullMessage = "event Test Event /at 12am today!";
        String[] correctOutput = {"Test Event", "12am today!"};
        String[] output = getArguments(fullMessage, "event");
        boolean isIdentical = Arrays.equals(output, correctOutput);
        assertEquals(true, isIdentical);
    }

    @Test
    public void getArguments_find() {
        String fullMessage = "find blah";
        String correctOutput = "blah";
        String output = getArgument(fullMessage);
        assertEquals(correctOutput, output);
    }
}
