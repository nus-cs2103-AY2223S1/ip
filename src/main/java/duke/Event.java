package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class event extends Task {
    protected formatDate day;

    /**
     *
     * @param desc description of the event
     */
    public event (String desc) {
        super(desc);
    }

    /**
     *
     * @return [E][ ] with description of event and the day of the event
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + day + ")";
    }

    /**
     *
     * @param day the day where the event is happening
     */
    public void setDay(formatDate day) {
        this.day = day;
    }

    /**
     *
     *@param str "MMM dd yyyy"
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
