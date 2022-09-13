package pikachu;

import java.util.Scanner;

/**
 * Represents the Ui for Pikachu chatbot. A <code>Ui</code> object corresponds to
 * Uis needed for Pikachu chatbot to communicate with the user.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Returns the scanner to read next line.
     *
     * @return the scanner to read next line.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Says bye for pikachu.
     * @return Pikachu's goodbye
     */
    public String sayBye() {
        return "Pi-ka...";
    }

    /**
     * Greets from pikachu.
     * @return Pikachu's greeting
     */
    public String sayHi() {
        return "Pika Pikachu! (I am Pikachu!)\nPi-ka-chu?(Do you need any help?)\n";
    }

    /**
     * Shows the loading error.
     */
    public void showLoadingError() {
        System.out.println("Pi? Pikapi!");
    }

    /**
     * Shows other any error.
     * @return Pikachu's confusion
     */
    public String showError(String error) {
        return error;
    }
}
