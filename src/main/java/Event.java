import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate date;
    private LocalTime time;

    public Event(String name, String dateAndTime) {
        super(name);
        this.date = getDateFromInput(dateAndTime);
        this.time = getTimeFromInput(dateAndTime);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " "
                + this.time.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + ")";
    }

    private String[] splitIntoDateAndTime(String string) {
        String[] token = string.split(" ", 2);
        return token;
    }

    private LocalDate getDateFromInput(String input) {
        String[] token = splitIntoDateAndTime(input);
        return LocalDate.parse(token[0], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    private LocalTime getTimeFromInput(String input) {
        String[] token = splitIntoDateAndTime(input);

        return LocalTime.parse(token[1], DateTimeFormatter.ofPattern("HHmm"));
    }

    private String[] splitIntoDateAndTime(String string) {
        String[] token = string.split("", 2);
        return token;
    }

    private String getDateFromInput(String input) {
        String[] token = splitIntoDateAndTime(input);
        return token[0];
    }

    private String getTimeFromInput(String input) {
        String[] token = splitIntoDateAndTime(input);
        return token[1];
    }
}
