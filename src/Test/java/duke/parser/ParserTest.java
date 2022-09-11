package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.FindCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parse_markNoTaskNumber_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "mark";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please put in a valid task number!"));
    }

    @Test
    public void parse_unmarkNoTaskNumber_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "unmark";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please put in a valid task number!"));
    }

    @Test
    public void parse_deleteNoTaskNumber_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "delete";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please put in a valid task number!"));
    }

    @Test
    public void parse_todo_success() {
        String str = "todo fishing";
        Command command = Parser.parse(str);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_todoNoDescription_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "todo";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please put in a description!"));
    }

    @Test
    public void parse_deadline_success() {
        String str = "deadline fishing /by 2022-12-12";
        Command command = Parser.parse(str);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_deadlineNoByKeyword_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "deadline fishing 2022-12-12";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please include '/by'!"));
    }

    @Test
    public void parse_deadlineNoDescription_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "deadline /by 2022-09-12";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please include a description!"));
    }

    @Test
    public void parse_deadlineNoDate_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "deadline fishing /by";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please include a date!"));
    }

    @Test
    public void parse_deadlineInvalidDateFormat_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "deadline fishing /by 19-11-2019";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Invalid date given!"));
    }

    @Test
    public void parse_deadlineInvalidDate_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "deadline fishing /by some date";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Invalid date given!"));
    }

    @Test
    public void parse_event_success() {
        String str = "event fishing /at 2022-12-12 2023-01-01";
        Command command = Parser.parse(str);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_eventNoAtKeyword_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "event fishing 2022-12-12 2022-12-14";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please include '/at'!"));
    }

    @Test
    public void parse_eventNoDescription_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "event /at 2022-09-12 2022-10-10";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please include a description!"));
    }

    @Test
    public void parse_find_success() {
        String str = "find fishing";
        Command command = Parser.parse(str);
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void parse_findNoDescription_throwsException() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            String str = "find";
            Command command = Parser.parse(str);
        });
        assertTrue(exception.getMessage().contains("Please include a description to search!"));
    }
}
