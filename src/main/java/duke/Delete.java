package duke;

import java.util.ArrayList;

/**
 * Represents the delete command.
 */
public class Delete extends Command {
    private int num;
    private String command;
    private ArrayList<String> arrayList;
    private int number;

    /**
     * Creates an object of Delete.
     *
     * @param description
     * @param number
     * @param num
     * @param arrayList
     */
    public Delete(String description, int number, int num, ArrayList<String> arrayList) {
        this.command = description;
        this.number = number - 2;
        this.num = num;
        this.arrayList = arrayList;
    }

    @Override
    public void execute() {
        arrayList.remove(num);
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
     * Returns a String representing the command Delete.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Noted. I've removed this task:\n" + this.command + "\n" + "Now you have " + this.number
                + " tasks in the list.";
    }
}
