package duke;

/**
 * UI Object to print expected UI feedback.
 */
public class UI {
    private final String lineBreak = "-".repeat(20);

    /**
     * Get the welcome message to the user.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Naruto and one day I will be Hokage! \nWhat can i do for you today Dattebayo?";
    }

    /**
     * Prints the welcome message to the user.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Naruto and one day I will be Hokage! \nWhat can i do for you today Dattebayo?");
    }

    /**
     * Returns the message when a command is not understood.
     */
    public String getDefaultMessage() {
        return "I have no clue whats going on here, but I'll act like i do Dattebayo, "
                + "use help to view the available commands desu.";
    }

    /**
     * Prints the message when a command is not understood.
     */
    public void printDefaultMessage() {
        System.out.println(this.getDefaultMessage());
    }

    /**
     * Prints the specified error message.
     * @param description String describing the error.
     */
    public void printErrorMessage(String description) {
        System.out.println("    Aw Shucks: " + description);
    }

    /**
     * Prints the list of commands a user can use.
     */
    public String getHelpMessage() {
        return "    Hi are you in need of help? These are the commands you can use Dattebayo:"
                + "\n      todo <Task>: add a task you need to do"
                + "\n      deadline <Task> /by <Deadline>: add a deadline you need to complete"
                + "\n      event <Task> /at <Time>: add an event you need to attend"
                + "\n      list: view all you tasks by index"
                + "\n      mark <Index>: mark an task as completed by index"
                + "\n      unmark <Index>: unmark a completed task by index"
                + "\n      delete <Index>: remove a specific task by index"
                + "\n      find <Name>: find a task by name"
                + "\n      bye: close the app and save the current task list";
    }

    public void printHelpMessage() {
        System.out.println(getHelpMessage());
    }

    /**
     * Get the goodbye message.
     */
    public String getGoodbyeMessage() {
        return "    Looks like this is the end for now, till we meet again! Ja Ne!";
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println(getGoodbyeMessage());
    }
}
