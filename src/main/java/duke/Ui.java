package duke;

/**
 * Class that deals with interactions with the user
 * @author Ashiqur Rahman A02030107Y
 */
public class Ui {

    /**
     * Constructor for Ui class
     */
    public Ui () {}

    /**
     * Method for greeting
     */
    public void greeting() {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        Parser.echo(greet);
    }

    /**
     * Method to create loading error
     */
    public static void showLoadingError() {
        Parser.echo("No data in duke.txt");
    }
}
