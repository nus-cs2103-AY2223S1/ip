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

    public static Deadline fromFileDescription(String input) {
        String[] strArray = input.split("\\|");
        String info = strArray[2];
        String by = strArray[3];
        Deadline deadline = new Deadline(info, by);
        if (strArray[1].equals("Y")) {
            deadline.donelah();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
