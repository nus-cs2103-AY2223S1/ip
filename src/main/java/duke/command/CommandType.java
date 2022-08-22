package duke.command;

public enum CommandType {
    LIST(0),
    CHECK(1),
    UNCHECK(1),
    TODO(1),
    DEADLINE(2),
    EVENT(2),
    DELETE(1),
    EXIT(0);

    private final int argsCount;

    CommandType(int argsCount) {
        this.argsCount = argsCount;
    }

    public boolean isCompatible(String[] args) {
        return this.argsCount == args.length;
    }
}
