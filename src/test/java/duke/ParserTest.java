package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void getTaskField_parser_taskDescriptionReturned() {
        String taskDescription = "borrow books";
        Parser parser = new Parser();
        String[] splitInput = new String[]{"event", taskDescription};
        assertEquals(parser.getTaskField(splitInput, 1, splitInput.length), taskDescription);
    }
}
