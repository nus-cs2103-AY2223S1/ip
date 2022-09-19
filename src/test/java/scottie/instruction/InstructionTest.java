package scottie.instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.instructions.Instruction;
import scottie.instructions.InvalidCommandException;


public class InstructionTest {
    @Test
    public void testInvalidCommandName() {
        try {
            Instruction.of("invalidCommandName", null, Map.of());
            fail("Instruction.of did not throw InvalidCommandException when given an invalid command name.");
        } catch (InvalidCommandException e) {
            assertEquals("invalidCommandName is not a valid command.", e.getMessage());
        }
    }
}
