package duke;
import javafx.application.Application;

/**
 * This class handles the launching of the Duke app
 */
public class Launcher {

    /**
     * The call to the main application function.
     *
     * @param args Arguments passed into the main function
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
