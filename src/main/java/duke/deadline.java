package duke;

import duke.Task;
import duke.formatDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class deadline extends Task {
    protected formatDate date;

    public deadline(String desc) {
        super(desc);
    }

    public deadline(String desc, formatDate date) {
        super(desc);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + date + ")";
    }

    public void setDate(formatDate date) {
        this.date = date;
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
