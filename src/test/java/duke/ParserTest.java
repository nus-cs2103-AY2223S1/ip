package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyTodoException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class ParserTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void parse_validCommand_success() throws DukeException {
        assertEquals(new ExitCommand().toString(),
                Parser.parse("bye").toString());
        assertEquals(new ListCommand().toString(),
                Parser.parse("list").toString());
        assertEquals(new MarkCommand(1).toString(),
                Parser.parse("mark 2").toString());
        assertEquals(new UnmarkCommand(2).toString(),
                Parser.parse("unmark 3").toString());
        assertEquals(new AddCommand(new Todo("blah blah")).toString(),
                Parser.parse("todo blah blah").toString());
        assertEquals(new AddCommand(new Deadline("test", "2022-08-14")).toString(),
                Parser.parse("deadline test /by 2022-08-14").toString());
        assertEquals(new AddCommand(new Event("test", "2022-08-14")).toString(),
                Parser.parse("event test /at 2022-08-14").toString());
        assertEquals(new DeleteCommand(1).toString(),
                Parser.parse("delete 2").toString());
    }

    @Test
    public void parse_emptyTodo_exceptionThrown() throws DukeException {
        try {
            assertEquals(0, Parser.parse("todo"));
        } catch (EmptyTodoException e) {
            assertEquals(":( OOPS!! The description of a todo cannot be empty",
                    e.getMessage());
        }
    }

    @Test
    public void parse_emptyDateField_exceptionThrown() throws DukeException {
        try {
            assertEquals(0, Parser.parse("deadline example /by"));
        } catch (EmptyDateException e) {
            assertEquals(":( OOPS!! The date field cannot be empty",
                    e.getMessage());
        }
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() throws DukeException {
        try {
            assertEquals(0, Parser.parse("blah blah blah"));
        } catch (UnknownCommandException e) {
            assertEquals(":( OOPS!! I'm sorry, but I don't know what this means :(",
                    e.getMessage());
        }
    }
}
