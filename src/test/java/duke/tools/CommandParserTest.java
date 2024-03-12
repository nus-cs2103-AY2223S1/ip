package duke.tools;

import duke.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {

    @Test
    public void markTest() {
        CommandParser testParser = new CommandParser("mark 1");
        try {
            CommandParser.Commands command = testParser.getCommand();
            assertEquals(CommandParser.Commands.MARK, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void unmarkTest() {
        CommandParser testParser = new CommandParser("unmark 1");
        try {
            CommandParser.Commands command = testParser.getCommand();
            assertEquals(CommandParser.Commands.UNMARK, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void listTest() {
        CommandParser testParser = new CommandParser("list");
        try {
            CommandParser.Commands command = testParser.getCommand();
            assertEquals(CommandParser.Commands.LIST, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void deleteTest() {
        CommandParser testParser = new CommandParser("delete 1");
        try {
            CommandParser.Commands command = testParser.getCommand();
            assertEquals(CommandParser.Commands.DELETE, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void todoTest() {
        CommandParser testParser = new CommandParser("todo read");
        try {
            CommandParser.Commands command = testParser.getCommand();
            assertEquals(CommandParser.Commands.TODO, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void deadlineTest() {
        CommandParser testParser = new CommandParser("deadline read /by 2022-08-31");
        try {
            CommandParser.Commands command = testParser.getCommand();
            assertEquals(CommandParser.Commands.DEADLINE, command);
        } catch (TaskNotFoundException e) {
            System.out.println("Caught TaskNotFoundException: " + e);
        }
    }

    @Test
    public void eventTest() {
        CommandParser testParser = new CommandParser("event read /at 2022-08-31");
        try {
            CommandParser.Commands command = testParser.getCommand();
            assertEquals(CommandParser.Commands.EVENT, command);
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
