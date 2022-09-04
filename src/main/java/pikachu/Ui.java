package pikachu;

import java.util.Scanner;

/**
 * Represents the Ui for Pikachu chatbot. A <code>Ui</code> object corresponds to
 * Uis needed for Pikachu chatbot to communicate with the user.
 */
public class Ui {

    static final String HORIZON = "____________________________________________________________\n";
    Scanner sc = new Scanner(System.in);

    /**
     * Returns the scanner to read next line.
     *
     * @return the scanner to read next line.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the format line.
     */
    public void showLine() {
        System.out.println(HORIZON);
    }

    /**
     * Says bye for pikachu
     */
    public void sayBye() {
        System.out.println("Pi-ka...");
    }

    /**
     * Greets from pikachu
     */
    public void sayHi() {
        System.out.println(HORIZON + "Pika Pikachu! (I am Pikachu!)\nPi-ka-chu?(Do you need any help?)\n" + HORIZON);
    }

    /**
     * Shows the loading error.
     */
    public void showLoadingError() {
        System.out.println("Pi? Pikapi!");
    }

    /**
     * Shows other any error.
     */
    public void showError(String error) {
        System.out.println(error);
    }
 }
