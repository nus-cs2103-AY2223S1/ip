package duke;

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

    public static void greetingMessage() {
        System.out.println("Hello! I'm Duke, what's up today?");
    }

    public static void handleFileNotFoundException() {
        System.out.println("File not found!");
    }

    public static void endingMessage() {
        System.out.println("See ya! Come again~"); //end of bot
    }
}
