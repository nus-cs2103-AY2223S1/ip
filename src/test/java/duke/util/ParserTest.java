package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.WrongCommand;
import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void parseTest() throws DukeException {
        String[] parseInputs = new String[] {"todo Clear Linear Algebra Homework",
                                             "deadline Cook Dinner /by 2019-05-03",
                                             "event Attend Taylor Swift Concert /at 2022-02-02",
                                             "mark 1",
                                             "unmark 1",
                                             "delete 1",
                                             "list",
                                             "invalid",
                                             "bye"};

        String[] commandInputs = new String[] {"Clear Linear Algebra Homework",
                                               "Cook Dinner /by 2019-05-03",
                                               "Attend Taylor Swift Concert /at 2022-02-02"};
        int[] commandTypes = new int[] {0, 1, 2};

        for (int i = 0; i < parseInputs.length; i++) {
            if (i < 3) {
                assertEquals(new AddCommand(commandTypes[i], commandInputs[i]), Parser.parse(parseInputs[i]));
            } else if (i == 3) {
                assertEquals(new MarkCommand(1), Parser.parse((parseInputs[i])));
            } else if (i == 4) {
                assertEquals(new UnmarkCommand(1), Parser.parse((parseInputs[i])));
            } else if (i == 5) {
                assertEquals(new DeleteCommand(1), Parser.parse((parseInputs[i])));
            } else if (i == 6) {
                assertTrue(Parser.parse(parseInputs[i]) instanceof ListCommand);
            } else if (i == 7) {
                assertTrue(Parser.parse(parseInputs[i]) instanceof WrongCommand);
            } else {
                assertTrue(Parser.parse(parseInputs[i]) instanceof ExitCommand);
            }
        }
    }
}
