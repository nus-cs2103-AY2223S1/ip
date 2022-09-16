package seedu.duke;

/**
 * Handles the user interface of the program.
 */
public class Ui {

    /** Greeting message */
    private final static String WELCOME = "Hello! I'm Luke\nWhat can I do for you?";
    /** Message to be displayed when a task is added */
    private final String ADDED_TASK = "Got it. I've added this task:";
    /** Message to be displayed when a task is removed */
    private final String REMOVED_TASK = "Noted. I've removed this task:";
    /** Message to be displayed when user exit the program */
    private final String GOODBYE = "Bye! Thanks for using Luke!";
   
    

    /**
     * A constructor for Ui
     */
    public Ui() {
        
    }

    /**
     * Prints the greeting message.
     */
    public static String showWelcome() {
        return WELCOME;
    }

 
    /**
     * Prints message after adding a task
     *
     * @param task The task added.
     */
    public String add(Task task) {
        return String.format("%s\n%s", ADDED_TASK, task);
    }

    /**
     * Prints the message after removing a task.
     *
     * @param task The task to be removed.
     */
    public String remove(Task task) {
        return String.format("%s\n%s", REMOVED_TASK, task);
    }

    /**
     * Prints the exit message.
     */
    public String showGoodbye() {
        return GOODBYE;
        
    }


}
