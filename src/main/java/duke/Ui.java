package duke;

/**
 *  A class which encapsulates the user interactions between Duke and the user.
 *  @author  Chen Guanzhou
 *  @version v1
 */
public class Ui {

    /**
     * A method for Duke to tell the user how many tasks they currently have.
     */
    public static void taskNumberMessage(TaskList taskList) {
        if (taskList.getLength() == 1) {
            System.out.println("Now you have 1 task in the list.");
        }
        else {
            System.out.println("Now you have " + taskList.getLength() + " tasks in the list.");
        }
    }

    /**
     * Greeting message when Duke is first executed.
     */
    public static void greetingMessage() {
        System.out.println("Hello! I'm Duke, what's up today?");
    }

    /**
     * Error message when file is not found when trying to read from a file.
     */
    public static void handleFileNotFoundException() {
        System.out.println("File not found!");
    }

    /**
     * Ending message after user terminates the program with "bye"
     */
    public static void endingMessage() {
        System.out.println("See ya! Come again~"); //end of bot
    }
}
