public class Event extends Task{
    private final char SYMBOL = 'E';
    private String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s (at: %s)", SYMBOL, super.toString(), this.timing);
    }

    @Override
    public String toStringFile() {
        return String.format("%c | %d | %s | %s", SYMBOL,
                super.getStatusIcon() == "X" ? 1 : 0, super.getDescription(), this.timing);
    }
}
