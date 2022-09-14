package pikachu;

import java.util.Scanner;

/**
 * Represents the Ui for Pikachu chatbot. A <code>Ui</code> object corresponds to
 * Uis needed for Pikachu chatbot to communicate with the user.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Says bye for pikachu.
     * @return Pikachu's goodbye
     */
    public String sayBye() {
        return "Pi-ka...(Say some last word to pikachu to comfort it!)";
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
}
