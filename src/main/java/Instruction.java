import java.util.Map;

public abstract class Instruction {
    private final String mainArgument;
    private final Map<String, String> flagArgumentsMap;

    public Instruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        this.mainArgument = mainArgument;
        this.flagArgumentsMap = flagArgumentsMap;
    }

    public static Instruction of(Command command, String mainArgument, Map<String, String> flagArgumentsMap) {
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
        default:
            // Only way execution can reach here is if a new Command was added
            // but the switch statement was not updated.
            throw new RuntimeException("Missing Instruction subclass for Command " + command.name());
        }
    }

    public boolean hasMainArgument() {
        return this.mainArgument != null;
    }

    public String getMainArgument() {
        return this.mainArgument;
    }

    public String getFlagArgument(String flag) {
        return this.flagArgumentsMap.get(flag);
    }

    public abstract void execute(TaskList taskList);
}
