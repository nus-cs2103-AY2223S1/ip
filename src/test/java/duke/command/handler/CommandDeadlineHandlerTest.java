package duke.command.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.command.CommandException;

class CommandDeadlineHandlerTest {

    @Test
    void deadlineHandler_normalInputsValidation_success() {
        List<String> normalInputs = Arrays.asList(
            "deadline task-1 /by 2022-08-24",
            "deadline task-2 /by 2022-08-24 23:59"
        );
        for (String normalInput : normalInputs) {
            try {
                new CommandDeadlineHandler(normalInput);
            } catch (CommandException ignored) {
                fail();
            }
        }
    }

    @Test
    void deadlineHandler_badInputsValidation_exception() {
        List<String> badInputs = Arrays.asList(
            "deadline",
            "deadline task-1 /by",
            "deadline /by 2022-08-24",
            "deadline task-1 /by abc",
            "deadline task-2 /by 2022-08",
            "deadline task-2 /by 23:59",
            "deadline task-1 /at 2022-08-24"
        );
        for (String badInput : badInputs) {
            try {
                new CommandDeadlineHandler(badInput);
                fail();
            } catch (Exception exception) {
                assertTrue(exception instanceof CommandException);
                assertEquals(CommandDeadlineHandler.INVALID_FORMAT_MESSAGE, exception.getMessage());
            }
        }
    }
}
