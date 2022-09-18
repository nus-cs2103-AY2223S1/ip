package duke.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class ListCommandTest extends CommandTest {

    @Test
    public void execute_list_success() throws DukeException {
        ListCommand listCommand = new ListCommand();
        String result = testCommandExecution(listCommand);;
        assertTrue(result.contains(TODO.toString()));
        assertTrue(result.contains(EVENT_ON_20220606.toString()));
        assertTrue(result.contains(DEADLINE_ON_20220505.toString()));
    }

}
