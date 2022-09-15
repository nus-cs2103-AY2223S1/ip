package duke.support;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

//Solution below adapted from https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
/**
 * Class to format dates and times inputted by users into
 * a specific format.
 *
 * @author lauralee
 */
public class DatesAndTimes {

    private LocalDate dateTime;

    /**
     * DatesandTimes class constructor.
     *
     * @param dateTime The date and time in the format inputted by the user.
     */
    public DatesAndTimes(String dateTime) {
        this.dateTime = LocalDate.parse(dateTime);
    }

    /**
     * Return date inputted by user in dd MMMM yy format.
     *
     * @return Date in dd MMMM yyyy format.
     */
    public String output() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }

}
