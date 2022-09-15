package neo;

/**
 * user interface class to interact with the user
 */
public class Ui {
    protected String inp;

    /**
     * Constructor for Ui class.
     */
    public Ui() {}

    /**
     * Prints welcome message.
     */
    public String printStart() {
        return "Hello! I'm Neo " + "Enter help for more details";
    }

    /**
     *
     * Prints goodbye message.
     */
    public String printEnd() {
        return "Exiting chat bot! Hope to see you again";
    }

    /**
     * Returns format of all commands.
     *
     * @return String
     */
    public String help() {
        String STR_1 = "Command format";
        String STR_2 = "list";
        String STR_3 = "deadline <description> /by <yyyy-mm-dd>";
        String STR_4 = "event <description> /by <yyyy-mm-dd>";
        String STR_5 = "todo <description>";
        String STR_6 = "delete <task number>";
        String STR_7 = "mark <task number>";
        String STR_8 = "unmark <task number>";
        String STR_9 = "low <task number>";
        String STR_10 = "medium <task number>";
        String STR_11 = "high <task number>";
        String STR_12 = "find <description>";
        String STR_13 = "bye";

        return STR_1 + "\n" + "\n" + STR_2 + "\n" + STR_3 + "\n" + STR_4 + "\n" + STR_5 + "\n" + STR_6 + "\n"
                + STR_7 + "\n" + STR_8 + "\n" + STR_9 + "\n" + STR_10 + "\n" + STR_11 + "\n" + STR_12 + "\n" + STR_13 + "\n";
    }

    /**
     * Returns the error message.
     *
     * @param e Error message.
     * @return String
     */
    public String errorMessage(String e) {
        return e;
    }
}