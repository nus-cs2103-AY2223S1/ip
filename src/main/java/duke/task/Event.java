package duke.task;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents an event which is a task with date and time.
 */
public class Event extends Task {

    /** Date of the event */
    private String date;

    /** Time of the event */
    private String time;

    /**
     * Constructs a new event task with given
     * description and date and time of the event.
     *
     * @param description of the event.
     * @param dateAndTime of the event.
     */
    public Event(String description, String dateAndTime) {
        super(description);
        this.date = getDateFromInput(dateAndTime);
        this.time = getTimeFromInput(dateAndTime);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " "
                + formatTime(time)
                + ")";
    }

    @Override
    public String toMemoryString() {
        return "E"
                + " | "
                + (this.isDone() ? "1" : "0")
                +  " | "
                + this.getName()
                + " | "
                + this.date
                + " "
                + this.time;
    }

    private String[] splitIntoDateAndTime(String string) {
        String[] token = string.split(" ", 2);
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

    private String formatTime(String str) {
        try {
            Date date = new SimpleDateFormat("HHmm").parse(str);
            SimpleDateFormat format = new SimpleDateFormat("h:mm a");
            return format.format(date);
        } catch (ParseException e) {
            System.out.println("There is some problem saving your event");
        }
        return time;
    }
}
