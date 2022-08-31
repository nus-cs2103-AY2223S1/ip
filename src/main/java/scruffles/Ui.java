package scruffles;

/**
 * This class is responsible for the user interactions of the program
 *
 * @author Shamus Tan
 */
public class Ui {

    /**
     * Greets the user when they first run the program
     */
    public void greet() {
        System.out.println("woof! I'm scruffles the task tracking doggo\n" + "what can I woof for you today?");
    }

    /**
     * A message that's displayed when there is no file found in the given filePath
     */
    public void showLoadingError() {
        System.out.println("woof file not found woof!");
    }

    /**
     * Prints a message that is given
     *
     * @param msg the message to be printed
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Greets the user when they exit the program
     */
    public void bye() {
        System.out.println("woof see you again woof!");
    }
}
