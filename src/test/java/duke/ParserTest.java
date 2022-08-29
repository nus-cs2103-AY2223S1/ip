package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    
    @Test
    void getKeyword_toDosInput() {
        String input = "todo borrow book";
        Parser parser = new Parser();
        String keyword = parser.getKeyword(input);
        assertEquals("todo", keyword);
    }

    @Test
    void parseInput_deadlineInput() {
        String input = "deadline return book /by 02/12/2019 18:30";
        Parser parser = new Parser();
        String[] output = parser.parseInput(input);
        String[] expected = {"deadline", "return book /by 02/12/2019 18:30"};
        assertArrayEquals(expected, output);
    }
}
