package arguments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.DukeException;
import input.Input;


public class StringArgumentTest {
    /**
     * Tests StringArgument for valid inputs
     */
    @Test
    public void argumentValidation_validInput() {
        try {
            Input valid = new InputStub("deadline /d description ");

            StringArgument s = new StringArgument(valid, "d", "Empty", "Missing");
            s.validate();
            assertEquals(s.getParameter(), "description");
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Tests StringArgument when the parameter is specified but an empty string
     */
    @Test
    public void argumentValidation_invalidInput_emptyParameter() {
        try {
            Input invalid = new InputStub("deadline /d");
            StringArgument s = new StringArgument(invalid, "d", "Empty", "Missing");
            s.validate();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Empty");
        }
    }

    /**
     * Tests StringArgument when parameter is not specified at all
     */
    @Test
    public void argumentValidation_invalidInput_missingParameter() {
        try {
            Input invalid = new InputStub("deadline ");
            StringArgument s = new StringArgument(invalid, "d", "Empty", "Missing");
            s.validate();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Missing");
        }
    }
}
