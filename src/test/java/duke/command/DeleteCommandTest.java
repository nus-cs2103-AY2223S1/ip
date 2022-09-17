package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class DeleteCommandTest extends CommandTest {
    @Test
    public void execute_oneDelete_success() throws DukeException {
        ArrayList<Integer> indexes = new ArrayList<>();
        indexes.add(1);
        DeleteCommand deleteCommand = new DeleteCommand(indexes);
        testCommandExecution(deleteCommand);
        assertEquals(INITIAL_TASK_COUNT - 1, getTasks().taskCount());
    }

    @Test
    public void execute_multipleDelete_success() throws DukeException {
        ArrayList<Integer> indexes = new ArrayList<>();
        indexes.add(1);
        indexes.add(2);
        DeleteCommand deleteCommand = new DeleteCommand(indexes);
        testCommandExecution(deleteCommand);
        assertEquals(INITIAL_TASK_COUNT - 2, getTasks().taskCount());
    }

    @Test
    public void execute_indexAboveCount_exceptionThrown() {
        ArrayList<Integer> indexes = new ArrayList<>();
        indexes.add(INITIAL_TASK_COUNT + 1);
        DeleteCommand deleteCommand = new DeleteCommand(indexes);
        assertThrows(DukeException.class, () -> testCommandExecution(deleteCommand));
    }

}
