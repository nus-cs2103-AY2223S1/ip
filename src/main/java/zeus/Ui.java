package zeus;

import java.util.Scanner;

/**
 * The user interface that the user sees.
 *
 * @author Derrick Khoo
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * The greeting from Duke that the user sees upon running a new instance of Duke
     */
    public String greet() {
        String output = "";
        output += "Hello! I'm Zeus\n";
        output += "What can I do for you?";
        output += "You can add your todos, deadlines and events,\n";
        output += "and i will keep track of them for you at no cost!";
        return output;
    }

    /**
     * The line that engulfs each message sent by Duke to the user.
     */
    public void line() {
        System.out.println("________________________________________");
    }

    /**
     * The method that returns the message that user inputs.
     * @return the message that user inputs
     */
    public String formatMessage(String msg) {
        return msg;
    }

    /**
     * The method that reads the input from the user.
     *
     * @return the string representation of the input from the user
     */
    public String parseCommand() {
        return this.scanner.nextLine();
    }
}
