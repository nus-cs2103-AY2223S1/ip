package duke;

import java.util.ArrayList;

/**
 * Represents the Mark command.
 */
public class Mark extends Parser {
    private String command;

    public Mark(String description, ArrayList<String> arrayList, int num) {
        super(description);
        this.command = description;
        arrayList.set(num, arrayList.get(num).substring(0, 3) + "[X]" + arrayList.get(num).substring(6));;
    }

    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
    @Override
    public boolean AddToList() {
        return false;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Nice! I've marked this task as done:\n" + this.command.substring(0, 3) + "[X]" + this.command.substring(6);
    }
}
