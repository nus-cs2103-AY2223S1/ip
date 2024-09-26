package tuna;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tuna.command.CommandType;
import tuna.utility.Parser;

/**
 * Tests to test the Parser class.
 */
public class ParserTest {

    /** Parser object used in the tests */
    private Parser parser = new Parser();

    /**
     * Tests if the bye command works as intended, which should return a ByeCommand object.
     *
     * @throws TunaException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_byeCommand_byeCommandInstanceReturned() throws TunaException {
        assertTrue(parser.parse("bye").getType().equals(CommandType.BYE));
    }

    /**
     * Tests if the list command works as intended, which should return a ListCommand object.
     *
     * @throws TunaException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_listCommand_listCommandInstanceReturned() throws TunaException {
        assertTrue(parser.parse("list").getType().equals(CommandType.LIST));
    }

    /**
     * Tests if the mark command works as intended, which should return a MarkItemCommand object.
     *
     * @throws TunaException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_markItemCommand_markItemCommandInstanceReturned() throws TunaException {
        assertTrue(parser.parse("mark 3").getType().equals(CommandType.MARK));
    }

    /**
     * Tests if the unmark command works as intended, which should return a UnMarkItemCommand object.
     *
     * @throws TunaException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_unMarkItemCommand_unMarkItemCommandInstanceReturned() throws TunaException {
        assertTrue(parser.parse("unmark 4").getType().equals(CommandType.UNMARK));
    }

    /**
     * Tests if the delete command works as intended, which should return a DeleteCommand object.
     *
     * @throws TunaException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_deleteCommand_deleteCommandInstanceReturned() throws TunaException {
        assertTrue(parser.parse("delete 5").getType().equals(CommandType.DELETE));
    }

    /**
     * Tests if the find command works as intended, which should return a FindCommand object.
     *
     * @throws TunaException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_findCommand_findCommandInstanceReturned() throws TunaException {
        assertTrue(parser.parse("find task").getType().equals(CommandType.FIND));
    }

    /**
     * Tests if the todo command works as intended, which should return a AddTodoCommand object.
     *
     * @throws TunaException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_addTodoCommand_addTodoCommandInstanceReturned() throws TunaException {
        assertTrue(parser.parse("todo new task").getType().equals(CommandType.TODO));
    }

    /**
     * Tests if the deadline command works as intended, which should return a AddDeadLineCommand object.
     *
     * @throws TunaException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_addDeadLineCommand_addDeadLineCommandInstanceReturned() throws TunaException {
        assertTrue(parser.parse("deadline new task /by 2022-08-22 12:00").getType()
                .equals(CommandType.DEADLINE));
    }

    /**
     * Tests if the event command works as intended, which should return a AddEventCommand object.
     *
     * @throws TunaException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_addEventCommand_addEventCommandInstanceReturned() throws TunaException {
        assertTrue(parser.parse("event new event /at 2022-08-22 12:00").getType()
                .equals(CommandType.EVENT));
    }
}
