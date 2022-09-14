package Duke;
import Command.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private boolean True;

    // test 1 : ListCommand
    @Test
    public void parserTest1() throws WagwanException {
        Parser parser1 = new Parser();
        String[] command = new String[]{"list"};
        Command expected1 = parser1.parse(command);
        assertEquals(expected1.toString(), "this is a list command");
    }

    // test 2 : MarkCommand
    @Test
    public void parserTest2() throws WagwanException {
        Parser parser2 = new Parser();
        String[] command = new String[]{"mark", "1"};
        Command expected2 = parser2.parse(command);
        assertEquals(expected2.toString(), "this is a mark command : mark 1");
    }

    // test 3 : UnmarkCommand
    @Test
    public void parserTest3() throws WagwanException {
        Parser parser3 = new Parser();
        String[] command = new String[]{"unmark", "5"};
        Command expected3 = parser3.parse(command);
        assertEquals(expected3.toString(), "this is an unmark command : unmark 5");
    }

    // test 4 : ExitCommand
    @Test
    public void parserTest4() throws WagwanException {
        Parser parser4 = new Parser();
        String[] command = new String[]{"bye"};
        Command expected4 = parser4.parse(command);
        assertEquals(expected4.toString(), "this is an exit command");
    }

    // test 5 : DeleteCommand
    @Test
    public void parserTest5() throws WagwanException {
        Parser parser5 = new Parser();
        String[] command = new String[]{"delete", "999"};
        Command expected5 = parser5.parse(command);
        assertEquals(expected5.toString(), "this is a delete command : delete 999");
    }

    // test 6 : TodoCommand
    @Test
    public void parserTest6() throws WagwanException {
        Parser parser6 = new Parser();
        String[] command = new String[]{"todo", "return book"};
        Command expected6 = parser6.parse(command);
        assertEquals(expected6.toString(), "this is a todo command : todo return book");
    }

    // test 7 : EventCommand
    @Test
    public void parserTest7() throws WagwanException {
        Parser parser7 = new Parser();
        String[] command = new String[]{"event", "return book /at 2022-08-08"};
        Command expected7 = parser7.parse(command);
        assertEquals(expected7.toString(), "this is an event command : event return book /at 2022-08-08");
    }

    // test 8 : DeadlineCommand
    @Test
    public void parserTest8() throws WagwanException {
        Parser parser8 = new Parser();
        String[] command = new String[]{"deadline", "return book /by 2022-08-08"};
        Command expected8 = parser8.parse(command);
        assertEquals(expected8.toString(), "this is a deadline command : deadline return book /by 2022-08-08");
    }

    // test 9 : DeadlineCommand
    @Test
    public void parserTest9() throws WagwanException {
        Parser parser9 = new Parser();
        String[] command = new String[]{"update", "1 read book"};
        Command expected9 = parser9.parse(command);
        assertEquals(expected9.toString(), "this is an update command : 1 read book");
    }
}
