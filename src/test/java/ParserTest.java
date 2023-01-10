import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.commands.Command;
import duke.commands.CommandsList;


public class ParserTest {
    @Test
    public void parseToDoTest() {
        Parser parser = new Parser();
        assertEquals(parser.parse("todo Help"), new Command(CommandsList.TODO, new String[]{"Help"}));
    }

    @Test
    public void parseDeadlineTest() {
        Parser parser = new Parser();
        assertEquals(parser.parse("deadline Help /by 2022-08-22"),
                new Command(CommandsList.DEADLINE, new String[]{"Help", "2022-08-22"}));
    }

    @Test
    public void parseEventTest() {
        Parser parser = new Parser();
        assertEquals(parser.parse("event Help /at 2022-08-22 10:49"),
                new Command(CommandsList.EVENT, new String[]{"Help", "2022-08-22 10:49"}));
    }

    @Test
    public void parseEmptyTest() {
        Parser parser = new Parser();
        assertEquals(parser.parse(""), new Command(CommandsList.UNKNOWN));
    }
}
