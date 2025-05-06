package duke;

import java.util.ArrayList;

/**
 * Represents the unmark command.
 */
public class Unmark extends Command {
    private String command;
    private ArrayList<String> arrayList;
    private int num;

    /**
     * Creates an object of unmark.
     *
     * @param description
     * @param arrayList
     * @param num
     */
    public Unmark(String description, ArrayList<String> arrayList, int num) {
        try {
            this.command = description;
            this.arrayList = arrayList;
            this.num = num;
        } catch (Exception e) {
            throw new DukeException("Sorry. The format for 'unmark' command should be unmark + space + number");
        }
    }

    @Override
    public void execute() {
        arrayList.set(num, arrayList.get(num).substring(0, 3) + "[ ]" + arrayList.get(num).substring(6));
    }

    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
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
        return "OK, I've marked this task as not done yet:\n" + this.command.substring(0, 3) + "[ ]"
                + this.command.substring(6);
    }
}
