package duke.instruction;

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
