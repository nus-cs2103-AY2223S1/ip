package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents the Event command.
 */
public class Events extends Parser {
    private String command;
    private int num;
    private String time;

    public Events(String description, int num, ArrayList<String> arrayList) {
        super(description);
        this.command = description.split("/")[0].substring(6);
        this.num = num;
        String time = description.split("/")[1].substring(3);
        LocalDate date = LocalDate.parse(time);
        String tranTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.time = tranTime;
        arrayList.add("[E][ ] " + description.split("/")[0].substring(6) + "(at: " + tranTime + ")");
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "[E][ ] " + this.command + "(at: " + time + ")\n"
                + "Now you have " + this.num + " tasks in the list.";
    }
}
