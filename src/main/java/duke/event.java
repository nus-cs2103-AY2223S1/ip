package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class event extends Task {
    protected formatDate day;

    public event (String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + day + ")";
    }

    public void setDay(formatDate day) {
        this.day = day;
    }

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
