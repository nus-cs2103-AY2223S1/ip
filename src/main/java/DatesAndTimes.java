import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * To format dates and times inputted by users into
 * a specific format.
 */
public class DatesAndTimes {

    private LocalDate dateTime;

    /**
     * DatesandTimes class constructor.
     *
     * @param dateTime the date and time in the format inputted by the user.
     */
    public DatesAndTimes(String dateTime) {
        this.dateTime = LocalDate.parse(dateTime);
    }

    /**
     * Return date inputted by user in dd MMMM yy format
     *
     * @return date in dd MMMM yyyy format
     */
    public String output() {
//        String month = this.dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }

}
