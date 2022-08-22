public class Deadline extends Task {
    private static final String type = "[D]";
    private String date;

    public Deadline(String name, int count, String date) throws MissingDescriptionException {
        super(name, count);
        this.date = "(by: " + date + ")";
    }

    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return String.format("%d." + type + comp + name + date, count);
    }

    @Override
    public String toStr() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return type + comp + name + date;
    }
}
