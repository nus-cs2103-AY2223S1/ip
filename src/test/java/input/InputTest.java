package input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.DukeException;




public class InputTest {
    /**
     * Tests Input with valid input string - command name, parameters, number of tokens correct
     */
    @Test
    public void inputInitialization_validInput() {
        String inputStringWithNoArguments = "deadline ";
        String inputString = " deadline /d deadline description /by 26-08-2022 1800 ";
        try {
            Input in = Input.newInput(inputStringWithNoArguments);
            Input inWithArgs = Input.newInput(inputString);
            assertEquals(in.getCommandName(), "deadline");
            assertEquals(inWithArgs.getCommandName(), "deadline");

            assertEquals(in.getNumberOfTokens(), 1);
            assertEquals(inWithArgs.getNumberOfTokens(), 7);

            assertEquals(inWithArgs.getParameter("d"), "deadline description");
            assertEquals(inWithArgs.getParameter("by"), "26-08-2022 1800");
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Tests Input with empty input string - correct exception thrown
     */
    @Test
    public void inputInitialization_invalidInput_emptyInput() {
        try {
            Input in = Input.newInput("");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Input should not be empty!");
        }
    }

    /**
     * Tests Input with invalid command format - correct exception thrown
     */
    @Test
    public void inputInitialization_invalidInput_commandName() {
        try {
            Input in = Input.newInput("/command");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), ("Commands do not start with / :)"));
        }
    }
}
