package duke.instruction;

import duke.support.DukeException;

/**
 * ExceptionInstruction class to call an exception when the parser is unable to understand the user's input.
 *
 * @author lauralee
 */
public class ExceptionInstruction implements Instruction {

    @Override
    public String execute() {
        return DukeException.taskException();
    }
}
