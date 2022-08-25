package arc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;


public class ParserTest {

    @Test
    public void parseDeadline_invalidDateFormat_dateTimeParseException() {
        Parser parser = new Parser(new Storage(""), new TaskList(new ArrayList<>()), new UI());
        String testInput = "deadline testTask /by 11 Aug 2022";

        try {
            parser.parse(testInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Date format should be dd/mm/yyyy" +
                    "\naRCommands:\n" +
                    "\tlist\n" +
                    "\ttodo [title]\n" +
                    "\tdeadline [title] /by [deadline]\n" +
                    "\tevent [title] /at [time]\n" +
                    "\tmark [index]\n" +
                    "\tunmark [index]\n" +
                    "\tdelete [index]", e.getMessage());
        }
    }
}
