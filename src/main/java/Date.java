import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {

    protected LocalDate date = LocalDate.now();
    protected int time;

    public Date(String str) throws DukeInvalidDateException {
        String[] splitted = str.split("\\s+");
        this.date = parse(splitted[0]);
        if (splitted.length > 1) {
            this.time = Integer.valueOf(splitted[1]);
        }
    }

    private LocalDate parse(String str) throws DukeInvalidDateException {
        LocalDate date;
        try {
            date = LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException();
        }
        return date;
    }

    private String dateToString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String timeToString() {
        int hours = (int) Math.floor(time / 100);
        int minutes = time % 100;
        String timeOfDay = "a.m.";
        String result = "";

        if (hours > 12) {
            hours -= 12;
            timeOfDay = "p.m.";
        } else if (hours == 12) {
            timeOfDay = "a.m.";
        }

        if (hours == 0) {
            result += "12.";
        } else {
            result += hours + ".";
        }

        if (minutes == 0) {
            result += "00";
        } else if (minutes < 10) {
            result += "0" + minutes;
        } else {
            result += minutes;
        }

        result += timeOfDay;
        return result;
    }

    @Override
    public String toString() {
        return dateToString() + " " + timeToString();
    }
}
