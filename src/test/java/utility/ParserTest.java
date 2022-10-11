package utility;

import static utility.Parser.parse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import command.AddTaskCommand;
import command.Command;
import exceptions.DukeException;

public class ParserTest {
    @Test
    public void parse_emptyInput_throwsDukeException() {
        Assertions.assertThrows(DukeException.class, () -> parse(""));
    }

    @Test
    public void parse_missingFieldsForTodo_throwsDukeException() {
        Assertions.assertThrows(DukeException.class, () -> parse("todo"));
    }

    @Test
    public void parse_allFieldsEnteredAsExcepted_createsAddTaskCommand() {
        Command atc = null;
        try {
            atc = parse("todo task1");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(atc instanceof AddTaskCommand);
    }

    @Test
    public void parse_invalidDateFieldForEvent_throwsDukeException() {
        Assertions.assertThrows(DukeException.class, () -> parse("event description 2022-31-31"));
    }

    @Test
    public void parse_emptyStringForEvent_throwsDukeException() {
        Assertions.assertThrows(DukeException.class, () -> parse("e 2022-04-04"));
    }
}
