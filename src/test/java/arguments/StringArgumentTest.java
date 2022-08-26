package arguments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.DukeException;
import input.Input;


public class StringArgumentTest {
    @Test
    public void argumentValidation_validInput() {
        try {
            Input valid = Input.newInput("deadline /d description ");
            StringArgument s = new StringArgument(valid, "d", "Empty", "Missing");
            s.validate();
            assertEquals(s.getParameter(), "description");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void argumentValidation_invalidInput_emptyParameter() {
        try {
            Input invalid = Input.newInput("deadline /d ");
            StringArgument s = new StringArgument(invalid, "d", "Empty", "Missing");
            s.validate();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Empty");
        }
    }

    @Test
    public void argumentValidation_invalidInput_missingParameter() {
        try {
            Input invalid = Input.newInput("deadline ");
            StringArgument s = new StringArgument(invalid, "d", "Empty", "Missing");
            s.validate();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Missing");
        }
    }
}
