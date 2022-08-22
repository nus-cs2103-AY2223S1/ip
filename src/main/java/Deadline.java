import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    /**
     * The due date or time of the Deadline instance.
     */
    protected String by;

    protected LocalDateTime dateTime;

    /**
     * Constructor for a Deadline instance.
     * @param description The description of the Deadline instance.
     * @param by The due date or time of the Deadline instance.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String date = by.split(" ")[0].trim();
        String time = by.split(" ")[1].trim();
        this.dateTime = LocalDateTime.parse(date + "T" + time.substring(0, 2) + ":" + time.substring(2));
    }

    private String getFormattedDateTime() {
        String s = "";
        s = s + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy "));
        String time = this.dateTime.toLocalTime().toString();
        int hours = Integer.parseInt(time.substring(0, 2));
        String meridiem = hours / 12 == 0 ? "am" : "pm";
        hours = hours % 12;
        s = s + hours + "." + time.substring(3) + meridiem;
        return s;
    }

    /**
     * Overrides the toString method in the parent class.
     * @return A string representing the current deadline.
     */
    @Override
    public String toString() {
        // return "[D]" + super.toString() + " (by: " + this.by + ")";
        return "[D]" + super.toString() + " (by: " + this.getFormattedDateTime() + ")";
    }

    public String getRemarks() {
        return this.by;
    }
}
