package byu.exceptions;

import byu.util.Instruction;

/**
 * An exception that indicates the description of an instruction is invalid.
 */
public class InvalidDescriptionException extends ByuException {

    private static final String NOT_AN_INTEGER_MESSAGE = "Description must be an integer!";
    private static final String WRONG_EVENT_FORMAT_MESSAGE =
            "Description of EVENT must be in the format (NAME /at DD/MM TT:TT to DD/MM TT:TT)!";
    private static final String WRONG_DEADLINE_FORMAT_MESSAGE =
            "Description of DEADLINE must be in the format (NAME /by DD/MM TT:TT)!";

    private final Instruction instruction;

    /**
     * Creates an exception that indicates the description of an instruction is invalid.
     * @param instruction the instruction in the user input.
     */
    public InvalidDescriptionException(Instruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public String getMessage() {
        String message;
        switch (instruction) {
        case MARK:
        case UNMARK:
        case DELETE:
            message = NOT_AN_INTEGER_MESSAGE;
            break;
        case EVENT:
            message = WRONG_EVENT_FORMAT_MESSAGE;
            break;
        case DEADLINE:
            message = WRONG_DEADLINE_FORMAT_MESSAGE;
            break;
        default:
            assert(instruction != Instruction.BYE);
            assert(instruction != Instruction.FIND);
            assert(instruction != Instruction.LIST);
            assert(instruction != Instruction.TODO);
            message = "The description is invalid!";
        }
        return message;
    }

}

