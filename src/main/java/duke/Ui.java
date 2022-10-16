package duke;

import task.Task;

/**
 *  A class which encapsulates the user interactions between Duke and the user.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class Ui {

    /**
     * A method for Duke to tell the user how many tasks they currently have.
     */
    public static String getTaskNumberMessage(TaskList taskList) {
        if (taskList.getLength() == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + taskList.getLength() + " tasks in the list.";
        }
    }

    /**
     * Greeting message when Duke is first executed.
     * @return
     */
    public static String greetingMessage() {
        return "Hello! I'm Duke, what's up today?";

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
    public static String endingMessage() {
        return "See ya! Come again~ \nClosing the program.."; //end of bot
    }

    /**
     * A task message to inform the user after task is added.
     */
    public static String addTaskMessage(Task t){
        String res = "Gotcha, I've added this task:\n";
        res += t + "\n";
        return res;
    }
}
