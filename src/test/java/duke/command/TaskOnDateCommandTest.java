package duke.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class TaskOnDateCommandTest extends CommandTest {

    @Test
    public void execute_findExistingDate_success() throws DukeException {
        TaskOnDateCommand taskOnDateCommand = new TaskOnDateCommand(LocalDate.parse("2022-05-05"));
        String result = testCommandExecution(taskOnDateCommand);
        assertTrue(result.contains(DEADLINE_ON_20220505.toString()));
    }

}
