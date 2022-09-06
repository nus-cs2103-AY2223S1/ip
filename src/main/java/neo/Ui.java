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
    public void printStart() {
        System.out.println("");
        System.out.println("Hello! I'm Neo");
        System.out.println("What can I do for you?");
    }
}