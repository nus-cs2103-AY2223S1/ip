public class Deadline extends Task {
    private final char SYMBOL = 'D';
    private String endDate;

    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s (by: %s)", SYMBOL, super.toString(), this.endDate);
    }

    @Override
    public String toStringFile() {
        return String.format("%c | %d | %s | %s", SYMBOL,
                super.getStatusIcon() == "X" ? 1 : 0, super.getDescription(), this.endDate);
    }
}