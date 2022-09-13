package exceptions;

import byu.Instruction;

/**
 * An exception that indicates the description of an instruction is empty.
 */
public class EmptyDescriptionException extends DukeException {

    private final Instruction instruction;

    /**
     * Creates an exception that indicates the description of an instruction is empty.
     * @param instruction the instruction type of the user input.
     */
    public EmptyDescriptionException(Instruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public String getMessage() {
        return "Description of a " + this.instruction + " cannot be empty!";
    }
}
