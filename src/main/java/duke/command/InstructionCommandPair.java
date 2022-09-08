package duke.command;

/**
 * A class that wraps a command and the string instruction of it.
 * @param <T> The actual type of command.
 */
public class InstructionCommandPair<T extends Command> {
    private final String instruction;
    private final T command;

    /**
     * The standard constructor.
     *
     * @param instruction The string instruction.
     * @param command The command object.
     */
    public InstructionCommandPair(String instruction, T command) {
        this.instruction = instruction;
        this.command = command;
    }

    /**
     * Command getter.
     *
     * @return Saved command object.
     */
    public T getCommand() {
        return command;
    }

    /**
     * Instruction getter.
     *
     * @return Saved string instruction.
     */
    public String getInstruction() {
        return instruction;
    }
}
