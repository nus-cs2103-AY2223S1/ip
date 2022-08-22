import java.time.LocalDate;

public class Deadline extends Task {
    private static final String type = "[D]";
    private LocalDate date;

    public Deadline(String name, int count, LocalDate date) throws MissingDescriptionException {
        super(name, count);
        this.date = date;
    }

    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        String dateString = date.getDayOfMonth() + " " + date.getMonth().toString() + " " + date.getYear();
        return String.format("%d." + type + comp + name + dateString, count);
    }

    @Override
    public String toStr() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        String dateString = date.getDayOfMonth() + " " + date.getMonth().toString() + " " + date.getYear();
        return type + comp + name + dateString;
    }
}
