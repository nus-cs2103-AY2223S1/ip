package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that inherits task
 */
public class Deadline extends Task {

    protected String by = description.substring(description.lastIndexOf("/") + 4);
    private DateTimeFormatter formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate date = LocalDate.parse(by, formatted);

    /**
     * Constructor of Deadline
     *
     * @param description what the task contains
     */
    public Deadline(String description) {
        super(description);
    }


    // Adapted from
    // https://stackoverflow.com/questions/14316487/
    // java-getting-a-substring-from-a-string-starting-after-a-particular-character



    /**
     * Returns the string representation of deadline
     *
     * @return string that is representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString().substring(0, 4) + getSubstring()
                + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    // To return substring before "/"
    // Adapted from
    // https://stackoverflow.com/questions/7683448/in-java-how-to-get-substring-from-a-string-till-a-character-c

    /**
     * Returns a string containing task to be done (ie. "Go shopping")
     *
     * @return string containing task to be done
     */
    private String getSubstring() {
        int index = description.indexOf("/");
        if (index != -1) {
            return description.substring(9, index);
        }
        return null;
    }
}

