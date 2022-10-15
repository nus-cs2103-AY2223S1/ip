package duke;

import java.util.ArrayList;

/**
 * Represents the Mark command.
 */
public class Mark extends Command {
    private String command;
    private int num;
    private ArrayList<String> arrayList;

    /**
     * Creates an object of Mark.
     *
     * @param description
     * @param arrayList
     * @param num
     */
    public Mark(String description, ArrayList<String> arrayList, int num) {
        try {
            this.command = description;
            this.num = num;
            this.arrayList = arrayList;
        } catch (Exception e) {
            throw new DukeException("Sorry. The format for 'mark' command should be mark + space + number");
        }
    }

    @Override
    public void execute() {
        arrayList.set(num, arrayList.get(num).substring(0, 3) + "[X]" + arrayList.get(num).substring(6));
    }
    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
    @Override
    public boolean addToList() {
        return false;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Nice! I've marked this task as done:\n" + this.command.substring(0, 3) + "[X]"
                + this.command.substring(6);
    }
}
