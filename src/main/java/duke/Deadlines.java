package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents the Deadlines command.
 */
public class Deadlines extends Parser {
    private String command;
    private int num;
    private String time;

    public Deadlines(String description, int num, ArrayList<String> arrayList) {
        super(description);
        this.command = description.split("/")[0].substring(9);
        this.num = num;
        String time = description.split("/")[1].substring(3);
        LocalDate date = LocalDate.parse(time);
        String tranTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.time = tranTime;
        arrayList.add("[D][ ] " + description.split("/")[0].substring(9) + "(by: " + tranTime + ")");
    }

    /**
     * Returns a String representing the command Deadlines.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "[D][ ] " + this.command + "(by: " + this.time + ")\n"
                + "Now you have " + this.num + " tasks in the list.";
    }
}
