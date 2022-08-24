package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * duke.Deadline is a duke.Task that accepts a task description and a deadline (dd-MM-yyyy)
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;


    /**
     * Constructor for duke.Deadline
     *
     * @param description description of the task
     * @param by          date in the format of dd-MM-yyyy
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    /**
     * Private constructor just to convert text in save file to deadlines.
     */
    private Deadline(String description, LocalDate byDate, boolean isDone) {
        super(description, isDone);
        this.byDate = byDate; // this is retrieved in save file, so format is MM d yyyy
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
            + "(by: " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    // duke.Deadline strings look like: [D][ ] return book (by: Jun 6 2022)
    // INPUTS look like deadline return book /by 06-06-2022
    public static Deadline stringToDeadline(String s) throws DukeException {
        if (!s.startsWith("[D][")) {
            throw new DukeException("This string is not a duke.Deadline string!");
        }
        char isDoneString = s.charAt(4); //[T][X] checks if X is present
        char X = 'X';
        boolean isDone = isDoneString == X;

        int idxOfBy = s.indexOf("(by:");

        String description = s.substring(7, idxOfBy);
        String byString = s.substring(idxOfBy + 5, s.length() - 1); // to avoid the brackets. byString looks like Aug 30 2022
        byString = byString.replace(" ", "-"); // byString looks like Aug-30-2022
        LocalDate byDate = LocalDate.parse(byString, DateTimeFormatter.ofPattern("MMM-d-yyyy"));
        return new Deadline(description, byDate, isDone);
    }

    public static void main(String[] args) throws DukeException {
        String testString = "[D][ ] return book (by: June 6th)";
        System.out.println(stringToDeadline(testString));
    }
}