package duke;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.InvalidCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void processDeadline() {
        String[] invalidInputs = {"deadline no deadline", "deadline name /by invalid date"};
        String validInput = "deadline name /by 01/01/2020 03:00";
        try {
            for (String input : invalidInputs) {
                Command command = Parser.parse(input);
                assertTrue(command instanceof InvalidCommand);
            }
            Command command = Parser.parse(validInput);
            assertTrue(command instanceof DeadlineCommand);
            assertEquals(new DeadlineCommand("name",
                            LocalDateTime.of(2020, 1, 1, 3, 0)),
                    command);

        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    void processDelete() {
    }
}