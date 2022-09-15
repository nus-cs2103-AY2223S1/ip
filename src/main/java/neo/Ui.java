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
     * Function to print welcome message
     */
    public String printStart() {
        return "Hello! I'm Neo " + "What can I do for you?";
    }

    /**
     *
     * Function to print goodbye message.
     */
    public String printEnd() {
        return "Exiting chat bot! Hope to see you again";
    }

    public String errorMessage(String e) {
        return e;
    }
}