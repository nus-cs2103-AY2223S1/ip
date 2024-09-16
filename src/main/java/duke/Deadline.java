package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * duke.Deadline is a duke.Task that accepts a task description and a deadline (dd-MM-yyyy)
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;

    private final static String DEADLINE_START_STR = "[D][";
    private final static int IS_DONE_INDEX = 4;


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

    /**
     * returns a Deadline from a string with format "[D][X] Description (by: MMM d yyyy)"
     * e.g. [D][ ] return book (by: Jun 6 2022)
     *
     * @param s String to convert into a Deadline
     * @throws DukeException when the string doesn't start with deadline tag [D]
     */
    public static Deadline stringToDeadline(String s) throws DukeException {
        if (!s.startsWith(DEADLINE_START_STR)) {
            throw new DukeException("This string is not a duke.Deadline string!");
        }
        char isDoneString = s.charAt(IS_DONE_INDEX); //[T][X] checks if X is present
        char X = 'X';
        boolean isDone = isDoneString == X;
        int idxOfBy = s.indexOf("(by:");
        assert(idxOfBy > 0);

        String description = s.substring(7, idxOfBy);

        // byString looks like Aug 30 2022
        String byString = s.substring(idxOfBy + 5, s.length() - 1); // to avoid the brackets.
        byString = byString.replace(" ", "-");
        LocalDate byDate = LocalDate.parse(byString, DateTimeFormatter.ofPattern("MMM-d-yyyy"));
        return new Deadline(description, byDate, isDone);
    }
}