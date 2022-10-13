package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents the Deadlines command.
 */
public class Deadlines extends Command {
    private String command;
    private int num;
    private String time;
    private ArrayList<String> arrayList;

    /**
     * Creates an object of Deadlines.
     *
     * @param description
     * @param num
     * @param arrayList
     */
    public Deadlines(String description, int num, ArrayList<String> arrayList) {
        try {
            this.command = description.split("/")[0].substring(9);
            this.num = num;
            this.arrayList = arrayList;
            String time = description.split("/")[1].substring(3);
            LocalDate date = LocalDate.parse(time);
            String tranTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.time = tranTime;
        } catch (Exception e) {
            throw new DukeException("Sorry. The format for 'deadline' command should be deadline + space + task"
                    + " + /by MMM d yyyy");
        }
    }

    @Override
    public void execute() {
        arrayList.add("[D][ ] " + command + "(by: " + this.time + ")");
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
