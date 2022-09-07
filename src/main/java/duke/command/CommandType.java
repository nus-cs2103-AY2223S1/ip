package duke.command;

/**
 * Supported commands along with their argument counts.
 */
public enum CommandType {
    HELP(1),
    LIST(1),
    CHECK(2),
    UNCHECK(2),
    TODO(2),
    DEADLINE(3),
    EVENT(3),
    FIND(2),
    DELETE(2),
    EXIT(1);

    private final int argCount;

    CommandType(int argCount) {
        this.argCount = argCount;
    }

    /**
     * Checks if the provided arguments are compatible with the command.
     *
     * @param args The arguments to check.
     * @return {@code true} if the arguments are compatible with the command, {@code false} otherwise.
     */
    public boolean isCompatible(String... args) {
        assert args != null;
        return this.argCount == args.length;
    }
}
