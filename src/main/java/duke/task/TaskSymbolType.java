package duke.task;

public enum TaskSymbolType {
    T("\uD83D\uDCDD"),
    D("⏰"),
    E("\uD83D\uDCC5");

    private final String symbol;

    TaskSymbolType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
