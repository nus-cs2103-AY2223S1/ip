import duke.Parser;
import duke.UI;
import duke.commands.Command;
import duke.commands.CommandsList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseToDoTest() {
        UI ui = new UI();
        Parser parser = new Parser(ui);
        assertEquals(parser.parse("todo Help"), new Command(CommandsList.TODO, new String[]{"Help"}));
    }

    @Test
    public void parseDeadlineTest() {
        UI ui = new UI();
        Parser parser = new Parser(ui);
        assertEquals(parser.parse("deadline Help /by 2022-08-22"),
                new Command(CommandsList.DEADLINE, new String[]{"Help", "2022-08-22"}));
    }

    @Test
    public void parseEventTest() {
        UI ui = new UI();
        Parser parser = new Parser(ui);
        assertEquals(parser.parse("event Help /at 2022-08-22 10:49"),
                new Command(CommandsList.EVENT, new String[]{"Help", "2022-08-22 10:49"}));
    }

    @Test
    public void parseEmptyTest() {
        UI ui = new UI();
        Parser parser = new Parser(ui);
        assertEquals(parser.parse(""), new Command(CommandsList.UNKNOWN));
    }
}
