package duke.util;

import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Handles the interactions with the user.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class Ui {
    private Scanner scanner;
    private Stage stage;

    /**
     * Creates an instance of Ui.
     */
    public Ui(Stage stage) {
        this.scanner = new Scanner(System.in);
        this.stage = stage;
    }

    /**
     * Crafts a welcome message for the user.
     *
     * @return String representation of a Welcome Message
     */
    public static String welcomeMessage() {
        return "________________________________________\n"
                + "Kon'nichiwa, I am Raijinmaru! A Fat Capybara!\n"
                + "How may I assist you today?\n"
                + "Type help for list of commands\n"
                + "________________________________________";
    }

    /**
     * Displays the error messages.
     *
     * @param errorMessage String representation of the error message to be shown
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Stops the ChatBot and closes the window after 5 seconds.
     */
    public void stopDuke() {
        PauseTransition closeWindow = new PauseTransition(Duration.seconds(5));
        closeWindow.setOnFinished(e -> this.stage.close());
        closeWindow.play();
    }
}
