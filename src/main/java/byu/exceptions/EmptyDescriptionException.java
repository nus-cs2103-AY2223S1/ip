package byu.exceptions;

import byu.util.Instruction;

/**
 * An exception that indicates the description of an instruction is empty.
 */
public class EmptyDescriptionException extends ByuException {

    private final Instruction instruction;

    /**
     * Creates an exception that indicates the description of an instruction is empty.
     * @param instruction the instruction in the user input.
     */
    public EmptyDescriptionException(Instruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public String getMessage() {
        return "Description of a " + this.instruction + " cannot be empty!";
    }
}
