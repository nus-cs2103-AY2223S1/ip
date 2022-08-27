package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class UiTest {
    private List<String> input = List.of("hello, how are you?", "Good bye!");

    @Test
    void joinsTextWithNewLine_listOfStrings_returnTextsSeparatedWithNewLine() {
        String expected = input.get(0) + "\n" + input.get(1);
        assertEquals(expected, Ui.joinTextsWithNewLine(input));
    }

    @Test
    void joinsTextWithNewLine_variableArguments_returnTextsSeparatedWithNewLine() {
        String expected = input.get(0) + "\n" + input.get(1);
        assertEquals(expected, Ui.joinTextsWithNewLine(input.get(0), input.get(1)));
    }
}
