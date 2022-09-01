package seedu.duke;

import java.util.ArrayList;

/**
 * Represents the User Interface, responsible for interacting with the user
 */

public class Ui {

    public static final String START = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String END = "Bye. Hope to see you again soon!";
    //private static final String BREAKER = "____________________________________________________________\n";

    private boolean isActive;

    /**
     * Displays the start message
     */
    public String start() {
        this.isActive = true;
        return msg(START);
    }

    /**
     * Displays the end message
     */
    public String end() {
        this.isActive = false;
        return msg(END);
    }

    /**
     * Returns the status of the Ui
     *
     * @return status of the Ui.
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Displays a message
     *
     * @param s String to be displayed
     */
    public String msg(String s) {
        return s + "\n";
    }

    /**
     * Displays the Tasks in a given ArrayList
     *
     * @param l ArrayList containing Tasks
     */
    public String displayList(ArrayList<Task> l) {
        String result = "";
        if (l.isEmpty()) {
            msg("");
            return "";
        }
        for (int i = 0; i < l.size()-1; i++) {
            result += (i+1) + ". " + l.get(i) + "\n";
        }
        result += (l.size()) + ". " + l.get(l.size()-1);
        return msg(result);
    }
}
