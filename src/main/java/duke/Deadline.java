package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected FormatDate date;

    /**
     *
     * @param description description of task
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     *
     * @return String : [D][ ] with the description and deadline
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + date + ")";
    }

    /**
     *
     * @param date set the deadline of the task
     */
    public void setDate(FormatDate date) {
        this.date = date;
    }

    /**
     *
     * @param str "MMM dd yyyy"
     * @return the correct String format "yyyy-MM-dd" which can be interpreted by the parser
     */
    public String correctDateFormat(String str) {
        SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat from = new SimpleDateFormat("MMM dd yyyy");
        String result = null;
        try {
            Date date = from.parse(str);
            result = to.format(date);
        } catch (ParseException e) {
            System.out.println("Invalid Date Format!");
        }
        return result;
    }
}
