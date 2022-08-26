import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    private String by;
    private LocalDate dateBy;

    public Deadline(String name, boolean isDone, String by) throws DukeTaskException {
        super(name, isDone);
        if (by.equals("") || by.equals(" ")) {
            throw new DukeTaskException("time can't be empty");
        }
        this.by = by;
        if (by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dateBy = LocalDate.parse(by);
        } else {
            throw new DukeTaskException("invalid date format");
        }
    }

    public static Deadline load(String s) throws DukeException {
        String by = s.substring(1, s.indexOf("|")).trim();
        String name = s.substring(s.indexOf("|") + 1, s.lastIndexOf("|")).trim();
        String isDone = s.substring(s.lastIndexOf("|") + 1).trim();
        return new Deadline(name, Boolean.parseBoolean(isDone), by);
    }

    @Override
    public String saveString() {
        return "D " + by + "|" + super.saveString();
    }

    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[D]" + temp + " (by: " + dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


}
