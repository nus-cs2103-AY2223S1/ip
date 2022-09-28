package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class FindCommandTest extends CommandTest {

    @Test
    public void execute_queryInTaskList_success() throws DukeException {
        // Test on part of EVENT's description
        String query = "cs1101s";
        FindCommand findCommand = new FindCommand(query);
        String result = testCommandExecution(findCommand);;
        assertTrue(result.contains(EVENT_ON_20220606.toString()));
    }

    @Test
    public void execute_queryNotInTaskList_success() throws DukeException {
        String query = "not found";
        FindCommand findCommand = new FindCommand(query);
        String result = testCommandExecution(findCommand);;
        assertEquals("Here are the tasks containing 'not found':", result);
    }

}
