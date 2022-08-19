package commands;

import enums.Messages;
import exceptions.DukeException;

public class InvalidCommand extends Display {
    private String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Validates the semantics of the commmand
     * Commands with no inputs are vacuously true
     * 
     * @return If the input parameters are valid
     */
    public boolean validate() {
        return true;
    }

    /**
     * Throws invalid command error
     * 
     * @throws DukeException
     */
    public void execute() throws DukeException {
        wrapWithLines(message);
    }
}
