package duke.tools;

import duke.exception.ContentNotFoundException;
import duke.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {

    @Test
    public void markTest() {
        CommandParser testParser = new CommandParser("mark 1");
        try {
            CommandParser.COMMANDS command = testParser.getCommand();
            assertEquals(CommandParser.COMMANDS.MARK, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void unmarkTest() {
        CommandParser testParser = new CommandParser("unmark 1");
        try {
            CommandParser.COMMANDS command = testParser.getCommand();
            assertEquals(CommandParser.COMMANDS.UNMARK, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void listTest() {
        CommandParser testParser = new CommandParser("list");
        try {
            CommandParser.COMMANDS command = testParser.getCommand();
            assertEquals(CommandParser.COMMANDS.LIST, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void deleteTest() {
        CommandParser testParser = new CommandParser("delete 1");
        try {
            CommandParser.COMMANDS command = testParser.getCommand();
            assertEquals(CommandParser.COMMANDS.DELETE, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void todoTest() {
        CommandParser testParser = new CommandParser("todo read");
        try {
            CommandParser.COMMANDS command = testParser.getCommand();
            assertEquals(CommandParser.COMMANDS.TODO, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void deadlineTest() {
        CommandParser testParser = new CommandParser("deadline read /by 2022-08-31");
        try {
            CommandParser.COMMANDS command = testParser.getCommand();
            assertEquals(CommandParser.COMMANDS.DEADLINE, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void eventTest() {
        CommandParser testParser = new CommandParser("event read /at 2022-08-31");
        try {
            CommandParser.COMMANDS command = testParser.getCommand();
            assertEquals(CommandParser.COMMANDS.EVENT, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void getTaskNo() {
        CommandParser testParser = new CommandParser("mark 1");
        int command = testParser.getTaskNo();
        assertEquals(1, command);
    }
}
