package duke.instruction;

//Solution below is adapted from
// https://github.com/nus-cs2103-AY2223S1/ip/pull/364/files#diff-f3fbe73abe70aea888eb7fc489f36e44e834a40faf7e006cc5907f6a9ee2a5c4
/**
 * Instruction interface with a single abstract method to execute an instruction.
 *
 * @author lauralee
 */
public interface Instruction {

    /**
     * Method which initiates the execution of an instruction.
     *
     * @return String description of the instruction that was executed.
     */
    public abstract String execute();

}
