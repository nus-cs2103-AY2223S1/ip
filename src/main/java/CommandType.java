enum CommandType {
    LIST(0),
    BYE(0),
    UPDATE_STATUS(1),
    TODO(1),
    BAD(0);

    private final int argsCount;

    CommandType(int argsCount) {
        this.argsCount = argsCount;
    }

    boolean isCompatible(String[] args) {
        return this.argsCount == args.length;
    }
}
