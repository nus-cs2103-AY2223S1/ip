package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseIndex_invalidInput_throwsException() {
        Parser p = new Parser();
        final List<String[]> invalidInputs = new ArrayList<>();
        invalidInputs.add(new String[]{"doesNotMatter", "notAnInteger"});
        invalidInputs.add(new String[]{"doesNotMatter", ""});
        for (String[] input : invalidInputs) {
            assertThrows(DukeException.class, () -> p.parseIndex(input));
        }
    }

    @Test
    public void parseTodo_emptyDescription_throwsException() {
        Parser p = new Parser();
        assertThrows(DukeException.class, () -> p.parseTodo(new String[]{"todo"}));
    }
}
