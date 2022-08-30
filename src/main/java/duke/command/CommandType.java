package duke.command;

/**
 * Supported commands along with their argument counts.
 */
public enum CommandType {
    LIST(0),
    CHECK(1),
    UNCHECK(1),
    TODO(1),
    DEADLINE(2),
    EVENT(2),
    FIND(1),
    DELETE(1),
    EXIT(0);

    private final int argsCount;

    CommandType(int argsCount) {
        this.argsCount = argsCount;
    }

    /**
     * Checks if the provided arguments are compatible with the command.
     *
     * @param args The arguments to check.
     * @return {@code true} if the arguments are compatible with the command, {@code false} otherwise.
     */
    public boolean isCompatible(String... args) {
        return this.argsCount == args.length;
    }
}
