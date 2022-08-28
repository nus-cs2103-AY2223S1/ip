package seedu.duke;

import java.util.ArrayList;

/**
 * Represents the User Interface, responsible for interacting with the user
 */

public class Ui {

    private static final String START = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String END = "Bye. Hope to see you again soon!";
    private static final String BREAKER = "____________________________________________________________\n";

    private boolean isActive;

    /**
     * Displays the start message
     */
    public void start() {
        this.isActive = true;
        this.msg(START);
    }

    /**
     * Displays the end message
     */
    public void end() {
        this.isActive = false;
        this.msg(END);
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
    public void msg(String s) {
        System.out.println(BREAKER + s + "\n" + BREAKER);
    }

    /**
     * Displays the Tasks in a given ArrayList
     *
     * @param l ArrayList containing Tasks
     */
    public void displayList(ArrayList<Task> l) {
        String result = "";
        if (l.isEmpty()) {
            msg("");
            return;
        }
        for (int i = 0; i < l.size()-1; i++) {
            result += (i+1) + ". " + l.get(i) + "\n";
        }
        result += (l.size()) + ". " + l.get(l.size()-1);
        msg(result);
    }
}
