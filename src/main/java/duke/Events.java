package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents the Event command.
 */
public class Events extends Command {
    private String command;
    private int num;
    private String time;
    private ArrayList<String> arrayList;

    /**
     * Creates an object of Events.
     *
     * @param description
     * @param num
     * @param arrayList
     */
    public Events(String description, int num, ArrayList<String> arrayList) {
        try {
            this.command = description.split("/")[0].substring(6);
            this.num = num;
            this.arrayList = arrayList;
            String time = description.split("/")[1].substring(3);
            LocalDate date = LocalDate.parse(time);
            String tranTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.time = tranTime;
        } catch (Exception e) {
            throw new DukeException("Sorry. The format for 'event' command should be event + space + task"
                    + " + /at time");
        }
    }

    @Override
    public void execute() {
        arrayList.add("[E][ ] " + command + "(at: " + time + ")");
    }

    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
    @Override
    public boolean addToList() {
        return true;
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
