package scottie.instructions;

import java.util.Map;
import java.util.Objects;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates an instruction from the user which when executed
 * performs some operation.
 */
public abstract class Instruction {
    private final String mainArgument;
    private final Map<String, String> flagArgumentsMap;

    /**
     * Constructs an Instruction with the given arguments.
     * This constructor is called by subclasses of Instruction.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    Instruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        this.mainArgument = mainArgument;
        this.flagArgumentsMap = flagArgumentsMap;
    }

    /**
     * Returns an instance of an Instruction subclass based on the
     * provided commandName. The arguments are then provided to that
     * instruction.
     *
     * @param commandName The name of the Command specifying what type of
     *                    Instruction to create.
     * @param mainArgument The main argument to provide to the instruction.
     * @param flagArgumentsMap The map between flags and values to provide
     *                         to the instruction.
     * @return The Instruction instance
     * @throws InvalidCommandException If the commandName does not correspond
     *                                 to an actual Command type.
     */
    public static Instruction of(String commandName, String mainArgument, Map<String, String> flagArgumentsMap)
            throws InvalidCommandException {
        Command command = Command.fromName(commandName);
        if (command == null) {
            throw new InvalidCommandException(commandName);
        }
        switch (command) {
        case LIST:
            return new ListInstruction(mainArgument, flagArgumentsMap);
        case MARK:
            return new MarkInstruction(mainArgument, flagArgumentsMap);
        case UNMARK:
            return new UnmarkInstruction(mainArgument, flagArgumentsMap);
        case TODO:
            return new TodoInstruction(mainArgument, flagArgumentsMap);
        case DEADLINE:
            return new DeadlineInstruction(mainArgument, flagArgumentsMap);
        case EVENT:
            return new EventInstruction(mainArgument, flagArgumentsMap);
        case DELETE:
            return new DeleteInstruction(mainArgument, flagArgumentsMap);
        case BYE:
            return new ByeInstruction(mainArgument, flagArgumentsMap);
        case FIND:
            return new FindInstruction(mainArgument, flagArgumentsMap);
        default:
            // Only way execution can reach here is if a new Command was added
            // but the switch statement was not updated.
            throw new RuntimeException("Missing Instruction subclass for Command " + command.name());
        }
    }

    /**
     * Returns whether this instruction has a main argument.
     *
     * @return Whether this instruction's main argument is not null.
     */
    boolean hasMainArgument() {
        return this.mainArgument != null;
    }

    /**
     * Returns the main argument provided to this instruction.
     *
     * @return The main argument provided to this instruction.
     */
    String getMainArgument() {
        return this.mainArgument;
    }

    /**
     * Returns the argument provided to this instruction with the given flag.
     *
     * @param flag The flag whose argument should be returned.
     * @return The argument corresponding to the given flag.
     */
    String getFlagArgument(String flag) {
        return this.flagArgumentsMap.get(flag);
    }

    /**
     * Executes this instruction using the given TaskList and Ui.
     *
     * @param taskList The TaskList to operate on.
     * @param ui The Ui used to display messages to the user.
     */
    public abstract void execute(TaskList taskList, Ui ui);

    /**
     * Returns whether this instruction ends the program.
     *
     * @return Whether this instruction ends the program.
     */
    public boolean endsProgram() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instruction that = (Instruction) o;
        return Objects.equals(this.mainArgument, that.mainArgument)
                && Objects.equals(this.flagArgumentsMap, that.flagArgumentsMap);
    }
}
