package arguments;

import exceptions.DukeException;
import input.Input;

/**
 * Class to mock Input class for testing purposes
 */
public class InputStub extends Input {
    private String stubInput;

    /**
     * Create InputStub
     * @param inputString Input string
     */
    public InputStub(String inputString) {
        super();
        stubInput = inputString;
    }

    /**
     * @param argument Argument to get parameter of
     * @return Parameter string
     * @throws DukeException if any issues occur
     */
    @Override
    public String getParameter(String argument) throws DukeException {
        assert argument.equals("d");
        if (stubInput.trim().equals("deadline /d description")) {
            return "description";
        } else if (stubInput.trim().equals(("deadline /d"))) {
            return "";
        } else if (stubInput.trim().equals("deadline")) {
            throw new DukeException("");
        }
        return null;
    }
}
