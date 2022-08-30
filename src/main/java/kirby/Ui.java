package kirby;

import java.util.Scanner;

/**
 * Ui class handles user interaction such as the different display messages.
 */
public class Ui {
    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hai I'm Kirby (੭｡╹▿╹｡)੭ your friendly chat assistant!! \n" + "What amazing plans do you have today?");
    }

    /**
     * Reads for a new input by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
