package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name, 'D');
        this.by = parseDateTime(by);
    }

    private String parseDateTime(String originalString) {
        StringBuffer deadline = new StringBuffer(originalString.replace("/","-"));

        if (Pattern.compile("([0-9]{4})[-]([1][0-2]|0[1-9])[-](3[01]|[12][0-9]|0[1-9])\\s[0-2][0-9][0-6][0-9]").matcher(deadline).matches()) { //yyyy-mm-dd HHmm
            deadline.insert(deadline.length() - 2, ":");
            deadline.replace(deadline.indexOf(" "), deadline.indexOf(" ") + 1,"T");
            return LocalDateTime.parse(deadline).format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        } else if (Pattern.compile("(3[01]|[12][0-9]|0[1-9])[-]([1][0-2]|0[1-9])[-][0-9]{4}\\s[0-2][0-9][0-6][0-9]").matcher(deadline).matches()) { //dd-mm-yyyy HHmm
            deadline.insert(deadline.length() - 2, ":");
            deadline.replace(deadline.indexOf(" "), deadline.indexOf(" ") + 1,"T");
            String dd = deadline.substring(0,2);
            deadline.replace(0,2, deadline.substring(6,10)).replace(8,12, dd);
            return LocalDateTime.parse(deadline).format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        } else if (Pattern.compile("([0-9]{4})[-]([1][0-2]|0[1-9])[-](3[01]|[12][0-9]|0[1-9])").matcher(deadline).matches()) { //yyyy-mm-dd
            return LocalDate.parse(deadline).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } else if (Pattern.compile("(3[01]|[12][0-9]|0[1-9])[-]([1][0-2]|0[1-9])[-][0-9]{4}").matcher(deadline).matches()) { //dd-mm-yyyy
            String dd = deadline.substring(0,2);
            deadline.replace(0,2, deadline.substring(6,10)).replace(8,12, dd);
            return LocalDate.parse(deadline).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
        return deadline.toString();
    }

    @Override
    public String toString() {
        return String.format(super.toString() + " (by: %s)", by);
    }
}
