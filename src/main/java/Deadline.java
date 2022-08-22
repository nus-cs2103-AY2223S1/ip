import java.time.LocalDate;

public class Deadline extends Task {
    private static final String type = "[D]";
    private LocalDate date;

    public Deadline(String name, LocalDate date) throws MissingDescriptionException {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        String dateString = date.getDayOfMonth() + " " + date.getMonth().toString() + " " + date.getYear();
        return type + comp + name + dateString;
    }

    @Override
    public String toData() {
        String type = "D";
        String completed = this.completed ? "1" : "0";
        String dt = date.toString();
        return type + "//" + completed +"//" + name + "//" + dt;
    }

}
