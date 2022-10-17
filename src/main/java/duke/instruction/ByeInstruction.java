package duke.instruction;

import duke.functions.Ui;

/**
 * ByeInstruction class to initiate a bye command inputted by the user.
 *
 * @author lauralee
 */
public class ByeInstruction implements Instruction {

    @Override
    public String execute() {
        return Ui.printBye();
    }
}
