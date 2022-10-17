package arc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseDeadline_invalidDateFormat_dateTimeParseException() {
        Parser parser = new Parser(new Storage("", ""),
                new TaskList(new ArrayList<>()), new NoteList(new ArrayList<>()));
        String testInput = "deadline testTask /by 11 Aug 2022";

        try {
            parser.parse(testInput);
            fail();
        } catch (DukeException e) {
            String message = "Date format should be dd/mm/yyyy";
            assertEquals(message + Duke.LIST_OF_ARC_COMMANDS, e.getMessage());
        }
    }
}
