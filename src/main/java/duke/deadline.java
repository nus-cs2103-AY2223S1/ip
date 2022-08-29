package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class deadline extends Task {
    protected formatDate date;

    /**
     *
     * @param desc description of task
     */
    public deadline(String desc) {
        super(desc);
    }

    /**
     *
     * @return String : [D][ ] with the description and due date
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + date + ")";
    }

    /**
     *
     * @param date set the due date of the task
     */
    public void setDate(formatDate date) {
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
