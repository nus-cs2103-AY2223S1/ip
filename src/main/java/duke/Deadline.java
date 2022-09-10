package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * A Deadline is a type of Task with a due date.
 */
public class Deadline extends Task {

    private String by;
    private LocalDate dateBy;

    /**
     * Constructs a Deadline.
     *
     * @param name Name of the deadline.
     * @param isDone Whether deadline has been marked.
     * @param by Due date of the deadline.
     * @throws DukeTaskException  If date format is not accepted.
     */
    public Deadline(String name, boolean isDone, String by) throws DukeTaskException {
        super(name, isDone);
        if (by.equals("") || by.equals(" ")) {
            throw new DukeTaskException("time can't be empty");
        }
        this.by = by;
        if (by.matches("\\d{4}-\\d{2}-\\d{2}") || by.matches("\\d{4}/\\d{2}/\\d{2}")) {
            this.dateBy = LocalDate.parse(by);
        } else {
            throw new DukeTaskException("invalid date format");
        }
    }

    /**
     * Loads a Deadline from the contents of a save file.
     *
     * @param s String representing the data of a deadline.
     * @return Deadline with appropriate data.
     * @throws DukeException  If date format is not accepted.
     */
    public static Deadline load(String s) throws DukeException {
        String by = s.substring(1, s.indexOf("|")).trim();
        String name = s.substring(s.indexOf("|") + 1, s.lastIndexOf("|")).trim();
        String isDone = s.substring(s.lastIndexOf("|") + 1).trim();
        return new Deadline(name, Boolean.parseBoolean(isDone), by);
    }

    /**
     * Returns representation of deadline data for saving.
     *
     * @return String representing deadline data.
     */
    @Override
    public String saveString() {
        return "D " + by + "|" + super.saveString();
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return Deadline as a string.
     */
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
