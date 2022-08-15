enum CommandType {
    LIST(0),
    UPDATE_STATUS(1),
    TODO(1),
    DEADLINE(2),
    EVENT(2),
    BYE(0),
    EMPTY(0),
    BAD(0);

    private final int argsCount;

    CommandType(int argsCount) {
        this.argsCount = argsCount;
    }

    boolean isCompatible(String[] args) {
        return this.argsCount == args.length;
    }
}
