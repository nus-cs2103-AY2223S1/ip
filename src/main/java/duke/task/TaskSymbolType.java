package duke.task;

/**
 * Supported task symbols in display and save formats.
 */
public enum TaskSymbolType {
    T("📝", 3),
    D("⏰", 4),
    E("📅", 4);

    private final String symbol;
    private final int argCount;

    TaskSymbolType(String symbol, int argCount) {
        this.symbol = symbol;
        this.argCount = argCount;
    }

    /**
     * Returns the display symbol for this task symbol type.
     *
     * @return The display symbol for this task symbol type.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Checks if the provided arguments are compatible with the task.
     *
     * @param args The arguments to check.
     * @return {@code true} if the arguments are compatible with the task, {@code false} otherwise.
     */
    public boolean isCompatible(String... args) {
        assert args != null;
        return this.argCount == args.length;
    }
}
