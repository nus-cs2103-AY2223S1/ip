package duke.task;

/**
 * Supported task symbols in display and save formats.
 */
public enum TaskSymbolType {
    T("\uD83D\uDCDD"),
    D("⏰"),
    E("\uD83D\uDCC5");

    private final String symbol;

    TaskSymbolType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the display symbol for this task symbol type.
     *
     * @return The display symbol for this task symbol type.
     */
    public String getSymbol() {
        return this.symbol;
    }
}
