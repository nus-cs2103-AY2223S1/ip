import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String itself, String by) {
        super(itself);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public boolean isOnDate(LocalDate localDate) {
        return by.equals(localDate);
    }

    @Override
    public String writeToFile() {
        return "D|" + super.writeToFile() + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
