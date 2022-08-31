package Duke;
import Command.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private boolean True;

    // test 1 : ListCommand
    @Test
    public void parserTest1() throws DukeException {
        Parser parser1 = new Parser();
        Command expected1 = parser1.parse("list");
        assertEquals(expected1.toString(), "this is a list command");
    }

    // test 2 : MarkCommand
    @Test
    public void parserTest2() throws DukeException {
        Parser parser2 = new Parser();
        Command expected2 = parser2.parse("mark_______________1");
        assertEquals(expected2.toString(), "this is a mark command : mark 1");
    }

    // test 3 : UnmarkCommand
    @Test
    public void parserTest3() throws DukeException {
        Parser parser3 = new Parser();
        Command expected3 = parser3.parse("unmark_______________5");
        assertEquals(expected3.toString(), "this is an unmark command : unmark 5");
    }

    // test 4 : ExitCommand
    @Test
    public void parserTest4() throws DukeException {
        Parser parser4 = new Parser();
        Command expected3 = parser4.parse("bye");
        assertEquals(expected3.toString(), "this is an exit command");
    }

    // test 5 : DeleteCommand
    @Test
    public void parserTest5() throws DukeException {
        Parser parser5 = new Parser();
        Command expected5 = parser5.parse("delete_______________999");
        assertEquals(expected5.toString(), "this is a delete command : delete 999");
    }

    // test 6 : TodoCommand
    @Test
    public void parserTest6() throws DukeException {
        Parser parser6 = new Parser();
        Command expected6 = parser6.parse("todo_______________return book");
        assertEquals(expected6.toString(), "this is a todo command : todo return book");
    }

    // test 7 : EventCommand
    @Test
    public void parserTest7() throws DukeException {
        Parser parser7 = new Parser();
        Command expected7 = parser7.parse("event_______________return book /at 2022-08-08");
        assertEquals(expected7.toString(), "this is an event command : event return book /at 2022-08-08");
    }

    // test 8 : DeadlineCommand
    @Test
    public void parserTest8() throws DukeException {
        Parser parser8 = new Parser();
        Command expected8 = parser8.parse("deadline_______________return book /by 2022-08-08");
        assertEquals(expected8.toString(), "this is a deadline command : deadline return book /by 2022-08-08");
    }
}
