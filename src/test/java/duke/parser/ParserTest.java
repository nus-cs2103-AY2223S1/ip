package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void addTodo() {
        Command c = Parser.parse("todo read book");
        assertEquals(new AddCommand(new Todo("read book")), c);
    }

    @Test
    public void addDeadline() {
        Command c = Parser.parse("deadline submit homework /by 2022-12-31");
        assertEquals(new AddCommand(new Deadline("submit homework", "2022-12-31")), c);
    }

    @Test
    public void addInvalidDeadline() {
        Exception exception = assertThrows(InvalidArgumentException.class, () -> {
            Parser.parse("deadline submit homework /by 21st September");
        });

        String expectedMessage = "☹ OOPS!!! Please format deadline request correctly.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void parseEmptyCommand() {
        Exception exception = assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("");
        });

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
